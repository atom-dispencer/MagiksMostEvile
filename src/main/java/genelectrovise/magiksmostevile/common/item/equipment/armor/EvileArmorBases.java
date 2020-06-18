package genelectrovise.magiksmostevile.common.item.equipment.armor;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public  class EvileArmorBases {

	public static class AmethystArmorBase extends ArmorItem {

		public AmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
			super(materialIn, slot, builder);
		}

		@Override
		public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
			if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
					&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
				if (player.inventory.armorItemInSlot(3).getItem() == EvileDeferredRegistry.AMETHYST_HELMET.get()
						&& player.inventory.armorItemInSlot(2).getItem() == EvileDeferredRegistry.AMETHYST_CHESTPLATE.get()
						&& player.inventory.armorItemInSlot(1).getItem() == EvileDeferredRegistry.AMETHYST_LEGGINGS.get()
						&& player.inventory.armorItemInSlot(0).getItem() == EvileDeferredRegistry.AMETHYST_BOOTS.get()) {
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 5, 0, false, false));
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 5, 0, false, false));
				}
			}
		}

	}

	public static class PoweredAmethystArmorBase extends ArmorItem {

		public PoweredAmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
			super(materialIn, slot, builder);
		}

		@Override
		public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
			if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
					&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
				if (player.inventory.armorItemInSlot(3).getItem() == EvileDeferredRegistry.POWERED_AMETHYST_HELMET.get()
						&& player.inventory.armorItemInSlot(2).getItem() == EvileDeferredRegistry.POWERED_AMETHYST_CHESTPLATE.get()
						&& player.inventory.armorItemInSlot(1).getItem() == EvileDeferredRegistry.POWERED_AMETHYST_LEGGINGS.get()
						&& player.inventory.armorItemInSlot(0).getItem() == EvileDeferredRegistry.POWERED_AMETHYST_BOOTS.get()) {
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 5, 1, false, false));
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 5, 0, false, false));
					player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 5, 0, false, false));
					player.addPotionEffect(new EffectInstance(Effects.HASTE, 5, 0, false, false));
				}
			}
		}

	}

	public static class OverPoweredAmethystArmorBase extends ArmorItem {

		public OverPoweredAmethystArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
			super(materialIn, slot, builder);
		}

		@Override
		public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
			if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
					&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
				if (player.inventory.armorItemInSlot(3).getItem() == EvileDeferredRegistry.OVER_POWERED_AMETHYST_HELMET.get()
						&& player.inventory.armorItemInSlot(2).getItem() == EvileDeferredRegistry.OVER_POWERED_AMETHYST_CHESTPLATE.get()
						&& player.inventory.armorItemInSlot(1).getItem() == EvileDeferredRegistry.OVER_POWERED_AMETHYST_LEGGINGS.get()
						&& player.inventory.armorItemInSlot(0).getItem() == EvileDeferredRegistry.OVER_POWERED_AMETHYST_BOOTS.get()) {
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 5, 2, false, false));
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 5, 0, false, false));
					player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 5, 0, false, false));
					player.addPotionEffect(new EffectInstance(Effects.HASTE, 5, 1, false, false));
					player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5, 2, false, false));
					player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5, 5, false, false));
					player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 5, 2, false, false));
					player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 5, 0, false, false));
				}
			}
		}

	}

}
