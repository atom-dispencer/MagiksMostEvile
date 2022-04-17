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
package genelectrovise.magiksmostevile.item.equipment.armor;

import genelectrovise.magiksmostevile.registry.orbital.ArmorOrbitalRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class EvileArmorBases {

    public static class AmethystArmorBase extends ArmorItem {

        public AmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot,
                                 Properties builder) {
            super(materialIn, slot, builder);
        }

        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (player.inventory.getArmor(0) != null && player.inventory.getArmor(1) != null
                    && player.inventory.getArmor(2) != null
                    && player.inventory.getArmor(3) != null) {
                if (player.inventory.getArmor(3).getItem() == ArmorOrbitalRegistry.AMETHYST_HELMET
                        .get()
                        && player.inventory.getArmor(2)
                        .getItem() == ArmorOrbitalRegistry.AMETHYST_CHESTPLATE.get()
                        && player.inventory.getArmor(1)
                        .getItem() == ArmorOrbitalRegistry.AMETHYST_LEGGINGS.get()
                        && player.inventory.getArmor(0).getItem() == ArmorOrbitalRegistry.AMETHYST_BOOTS
                        .get()) {

                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 0, false, false));
                    player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 201, 0, false, false));

                }
            }
        }

    }

    public static class PoweredAmethystArmorBase extends ArmorItem {

        public PoweredAmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot,
                                        Properties builder) {
            super(materialIn, slot, builder);
        }

        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (player.inventory.getArmor(0) != null && player.inventory.getArmor(1) != null
                    && player.inventory.getArmor(2) != null
                    && player.inventory.getArmor(3) != null) {
                if (player.inventory.getArmor(3)
                        .getItem() == ArmorOrbitalRegistry.POWERED_AMETHYST_HELMET.get()
                        && player.inventory.getArmor(2)
                        .getItem() == ArmorOrbitalRegistry.POWERED_AMETHYST_CHESTPLATE.get()
                        && player.inventory.getArmor(1)
                        .getItem() == ArmorOrbitalRegistry.POWERED_AMETHYST_LEGGINGS.get()
                        && player.inventory.getArmor(0)
                        .getItem() == ArmorOrbitalRegistry.POWERED_AMETHYST_BOOTS.get()) {

                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 1, false, false));
                    player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 201, 0, false, false));
                    player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 0, false, false));
                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20, 0, false, false));

                }
            }
        }

    }

    public static class OverPoweredAmethystArmorBase extends ArmorItem {

        public OverPoweredAmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot,
                                            Properties builder) {
            super(materialIn, slot, builder);
        }

        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (player.inventory.getArmor(0) != null && player.inventory.getArmor(1) != null
                    && player.inventory.getArmor(2) != null
                    && player.inventory.getArmor(3) != null) {
                if (player.inventory.getArmor(3)
                        .getItem() == ArmorOrbitalRegistry.OVER_POWERED_AMETHYST_HELMET.get()
                        && player.inventory.getArmor(2)
                        .getItem() == ArmorOrbitalRegistry.OVER_POWERED_AMETHYST_CHESTPLATE.get()
                        && player.inventory.getArmor(1)
                        .getItem() == ArmorOrbitalRegistry.OVER_POWERED_AMETHYST_LEGGINGS.get()
                        && player.inventory.getArmor(0)
                        .getItem() == ArmorOrbitalRegistry.OVER_POWERED_AMETHYST_BOOTS.get()) {

                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 2, false, false));
                    player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 201, 0, false, false));
                    player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 0, false, false));
                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20, 1, false, false));
                    player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 20, 2, false, false));
                    player.addEffect(new EffectInstance(Effects.REGENERATION, 20, 20, false, false));
                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20, 2, false, false));
                    player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 20, 0, false, false));

                }
            }
        }

    }

}
