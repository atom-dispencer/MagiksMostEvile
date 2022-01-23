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
package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.tileentity.altar.AltarBlock;
import genelectrovise.magiksmostevile.tileentity.amethyst_crystal.AmethystCrystalBlock;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableBlock;
import genelectrovise.magiksmostevile.tileentity.mortar.MortarBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * {@link Blocks}
 *
 * @author GenElectrovise
 */
public class BlockOrbitalRegistry implements IOrbitalRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagiksMostEvile.MODID);

    // =========BLOCKS======================================================================================================================
    public static final RegistryObject<Block> AMETHYST_BLOCK = BLOCKS.register("amethyst_block", () -> new Block(
            Block.Properties.of(Material.GLASS, MaterialColor.COLOR_MAGENTA).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).lightLevel((state) -> 5 / 16).strength(3F, 3F)));
    public static final RegistryObject<Block> LEAD_BLOCK =
            BLOCKS.register("lead_block", () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).strength(5F, 6F)));

    // Amethyst Ore
    public static final RegistryObject<Block> AMETHYST_ORE_OVERWORLD = BLOCKS.register("amethyst_ore_overworld",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(1.5F, 6F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_ORE_NETHER = BLOCKS.register("amethyst_ore_nether",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.NETHER).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(0.4F, 0.4F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_ORE_END = BLOCKS.register("amethyst_ore_end",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.SAND).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(3F, 9F).requiresCorrectToolForDrops()));

    // Lead Ore
    public static final RegistryObject<Block> LEAD_ORE_OVERWORLD = BLOCKS.register("lead_ore_overworld",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(1.5F, 6F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LEAD_ORE_NETHER = BLOCKS.register("lead_ore_nether",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.NETHER).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(0.4F, 0.4F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LEAD_ORE_END = BLOCKS.register("lead_ore_end",
            () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.SAND).harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL_BLOCK).strength(3F, 9F).requiresCorrectToolForDrops()));

    // Tile entities
    public static final RegistryObject<Block> AMETHYST_CRYSTAL = BLOCKS.register("amethyst_crystal",
            () -> new AmethystCrystalBlock(Block.Properties.of(Material.GLASS, MaterialColor.COLOR_MAGENTA).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).strength(2F, 10F)));
    public static final RegistryObject<Block> ALTAR =
            BLOCKS.register("altar", () -> new AltarBlock(Block.Properties.of(Material.GLASS, MaterialColor.SAND).harvestTool(ToolType.PICKAXE).sound(SoundType.WOOL).strength(6F, 10F)));
    public static final RegistryObject<Block> INSCRIPTION_TABLE =
            BLOCKS.register("inscription_table", () -> new InscriptionTableBlock(Block.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MORTAR = BLOCKS.register("mortar", () -> new MortarBlock(Block.Properties.of(Material.DECORATION).instabreak().noOcclusion()));

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public void initialise() {
        OrbitalRegistryGenerator.registerDeferredRegister(BLOCKS);
    }

    @Override
    public String name() {
        return "blocks";
    }

}
