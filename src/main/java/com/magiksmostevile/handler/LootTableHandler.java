package com.magiksmostevile.handler;

import com.magiksmostevile.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
    public static final ResourceLocation VAMPIRE_BAT_LOOT = LootTableList.register(new ResourceLocation(Main.MODID, "vampire_bat_loot"));
}
