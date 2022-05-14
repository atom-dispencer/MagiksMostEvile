/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.world.structure.shrine;

import com.google.common.collect.Lists;
import com.mojang.serialization.Dynamic;
import genelectrovise.magiksmostevile.world.EnumFeatureLocation;
import genelectrovise.magiksmostevile.world.StructureUtil;
import genelectrovise.magiksmostevile.world.structure.registry.StructurePieces;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.util.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.RuinedPortalPiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * {@link RuinedPortalPiece}
 *
 * @author GenElectrovise
 */
public class OvergroundShrineStructurePiece extends TemplateStructurePiece {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ResourceLocation templateName;
    private final Rotation rotation;
    private final Mirror mirror;
    private final EnumFeatureLocation location;
    private final OvergroundShrineStructureAestheticsSerializer serializer;


    /**
     * Called from the {@link OvergroundShrineStructure} class.
     *
     * @param blockPos
     * @param location
     * @param serializer
     * @param templateName
     * @param template
     * @param rotation
     * @param mirror
     * @param truePosition
     */
    public OvergroundShrineStructurePiece(BlockPos blockPos, EnumFeatureLocation location, OvergroundShrineStructureAestheticsSerializer serializer, ResourceLocation templateName, Template template,
                                          Rotation rotation, Mirror mirror, BlockPos truePosition) {
        super(StructurePieces.OVERGROUND_SHRINE_PIECE, 0);
        this.templatePosition = blockPos;
        this.templateName = templateName;
        this.rotation = rotation;
        this.mirror = mirror;
        this.location = location;
        this.serializer = serializer;
        this.setupPlacement(template, truePosition);
    }

    /**
     * Used to read from nbt
     *
     * @param templateManager
     * @param nbt
     */
    public OvergroundShrineStructurePiece(TemplateManager templateManager, CompoundNBT nbt) {
        super(StructurePieces.OVERGROUND_SHRINE_PIECE, nbt);

        // Basic
        this.templateName = new ResourceLocation(nbt.getString("Template"));
        this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
        this.mirror = Mirror.valueOf(nbt.getString("Mirror"));
        this.location = EnumFeatureLocation.get(nbt.getString("Location"));

        // Properties
        this.serializer = OvergroundShrineStructureAestheticsSerializer.CODEC
                .parse(new Dynamic<>(NBTDynamicOps.INSTANCE, nbt.get("Properties")))
                .getOrThrow(true, LOGGER::error);

        // Fetch Template
        Template template = templateManager.get(this.templateName);

        // Setup
        this.setupPlacement(template,
                new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2));
    }

    public static Heightmap.Type getHeightMapType(EnumFeatureLocation location) {
        return location == EnumFeatureLocation.OCEAN ? Heightmap.Type.OCEAN_FLOOR_WG
                : Heightmap.Type.WORLD_SURFACE_WG;
    }

    private void setupPlacement(Template template, BlockPos centerOffset) {

        // Add processor to read the NBT ignoring structure blocks and air
        BlockIgnoreStructureProcessor blockignorestructureprocessor = BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR;

        // Create a list of rules for replacing blocks
        List<RuleEntry> list = Lists.newArrayList();

        // Replace
        list.add(StructureUtil.randomBlockReplacementRule(Blocks.STONE_BRICKS, 0.2f, Blocks.AIR));
        if (!this.serializer.isCold) {
            list.add(
                    StructureUtil.randomBlockReplacementRule(Blocks.NETHERRACK, 0.07F, Blocks.MAGMA_BLOCK));
        }

        PlacementSettings placementsettings = new PlacementSettings() // New
                .setRotation(this.rotation) // Rotation
                .setMirror(this.mirror) // Mirror
                .setRotationPivot(centerOffset) // Center offset
                .addProcessor(blockignorestructureprocessor) // Processor (ignore some)
                .addProcessor( // Add rules
                        new RuleStructureProcessor(list)) // > from list
                .addProcessor(// Mossiness
                        new BlockMosinessProcessor( // > Processor
                                this.serializer.mossiness)) // > serializer
                .addProcessor( // Lava
                        new LavaSubmergingProcessor()); // > +water

        // Replace stone variants with blackstone
        if (this.serializer.replaceWithBlackstone) {
            placementsettings.addProcessor(BlackStoneReplacementProcessor.INSTANCE);
        }

        this.setup(template, this.templatePosition, placementsettings);
    }

    /**
     * (abstract) Called on loading the structure to read data from the java to the NBT tag!
     */
    @Override
    protected void addAdditionalSaveData(CompoundNBT tagCompound) {
        super.addAdditionalSaveData(tagCompound);
        // Basic
        tagCompound.putString("Template", this.templateName.toString());
        tagCompound.putString("Rotation", this.rotation.name());
        tagCompound.putString("Mirror", this.mirror.name());
        tagCompound.putString("Location", this.location.getName());

        // Properties
        OvergroundShrineStructureAestheticsSerializer.CODEC
                .encodeStart(NBTDynamicOps.INSTANCE, this.serializer).resultOrPartial(LOGGER::error)
                .ifPresent((inbt) -> {
                    tagCompound.put("Properties", inbt);
                });
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
                                    MutableBoundingBox sbb) {
        // Nothing in RuinedPortalPiece, used by the igloo to give its chest a loot table.
    }

}
