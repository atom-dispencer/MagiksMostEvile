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
package genelectrovise.magiksmostevile.world.gen.structure.shrine;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.world.gen.EnumFeatureLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.RuinedPortalStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * See also {@link RuinedPortalStructure}
 *
 * @author GenElectrovise
 */
public class OvergroundShrineStructure extends Structure<OvergroundShrineFeatureConfig> {

    public static final int bigVariants = 1;
    public static final int mediumVariants = 3;
    public static final int smallVariants = 3;

    public OvergroundShrineStructure() {
        super(OvergroundShrineFeatureConfig.CODEC);
    }

    @Override
    public IStartFactory<OvergroundShrineFeatureConfig> getStartFactory() {
        return OvergroundShrineStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return Decoration.SURFACE_STRUCTURES;
    }

    ;

    protected static enum Size {
        SMALL("small", 3), MEDIUM("medium", 3), LARGE("large", 1);

        private String name;
        private int count;

        private Size(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

    }

    public static class Start extends StructureStart<OvergroundShrineFeatureConfig> {

        public Start(Structure<OvergroundShrineFeatureConfig> structure, int chunkPosX, int chunkPosZ,
                     MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }

        public static boolean isPlacementPositionCold(BlockPos pos, Biome biome) {
            return biome.getTemperature(pos) < 0.15F;
        }

        /**
         * <ul>
         * <li>Sets serializer variables to customise the structure aesthetics to the location.
         * <li>Select a random variant of the structure
         * <li>Piece together data for the structure
         * <li>Create {@value components} list of {@link StructurePiece}s. I suppose these will then be
         * built automatically.
         * </ul>
         */
        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkPosX, int chunkPosZ, Biome biome,
                                   OvergroundShrineFeatureConfig configuration) {

            OvergroundShrineStructureAestheticsSerializer serializer = new OvergroundShrineStructureAestheticsSerializer();
            EnumFeatureLocation location = configuration.location;

            // Get the location of the template of the structure, i.e. chosen randomly to determine size
            ResourceLocation templateName = generateTemplateName();

            // Create basic final data
            Template template = templateManager.get(templateName);

            if (template == null)
                throw new NullPointerException("Could not find template for " + getClass().getName() + " by name " + templateName);

            Rotation rotation = Util.getRandom(Rotation.values(), random);
            Mirror mirror = Util.getRandom(Mirror.values(), random);
            BlockPos truePosition = new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
            BlockPos chunkBlockPos = new ChunkPos(chunkPosX, chunkPosZ).getWorldPosition();
            MutableBoundingBox mutableboundingbox = template.getBoundingBox(chunkBlockPos, rotation, truePosition, mirror);

            // Create bounding boxes
            Vector3i vector3i = mutableboundingbox.getCenter();
            int x = vector3i.getX();
            int z = vector3i.getZ();
            int y = chunkGenerator.getBaseHeight(x, z, OvergroundShrineStructurePiece.getHeightMapType(location)) - 1;
            BlockPos finalPlacementPosition = new BlockPos(x, y, z);

            // Is the position cold?
            if (configuration.location == EnumFeatureLocation.ICY
                    || configuration.location == EnumFeatureLocation.OCEAN
                    || configuration.location == EnumFeatureLocation.END) {

                serializer.isCold = isPlacementPositionCold(finalPlacementPosition, biome);
            }

            this.pieces.add(new OvergroundShrineStructurePiece(finalPlacementPosition, location,
                    serializer, templateName, template, rotation, mirror, truePosition));
            this.calculateBoundingBox();
        }

        private ResourceLocation generateTemplateName() {
            float randomisedFloat = this.random.nextFloat();
            Size size = Size.SMALL;
            // Make it big (5%)
            if (randomisedFloat < 0.05F) {
                size = Size.LARGE;
            }
            // Make it medium (25%)
            else if (randomisedFloat < 0.35) {
                size = Size.MEDIUM;
            }
            // Make it small (65%)
            else {
                size = Size.SMALL;
            }

            return getLocationOfSizedTemplate(size);
        }

        /**
         * @param size
         * @return The name of a random shrine template
         */
        private ResourceLocation getLocationOfSizedTemplate(Size size) {

            int variantCount = size.getCount();

            StringBuilder builder = new StringBuilder("overground_shrine/"); // overground_shrine/
            appendSize(size, builder); // overground_shrine/large
            builder.append("_"); // overground_shrine/large_
            appendVariantNumber(variantCount, builder); // overground_shrine/large_0

            return new ResourceLocation(MagiksMostEvile.MODID, builder.toString());
        }


        /**
         * overground_shrine/ + large
         *
         * @param size
         * @param builder
         */
        private void appendSize(Size size, StringBuilder builder) {
            builder.append(size.getName());
        }

        /**
         * overground_shrine/large_ + 0
         *
         * @param variantCount
         * @param builder
         */
        private void appendVariantNumber(int variantCount, StringBuilder builder) {
            int choice = this.random.nextInt(variantCount);
            builder.append(choice);
        }

    }

}


