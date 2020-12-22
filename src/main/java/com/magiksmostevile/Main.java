package com.magiksmostevile;

import org.lwjgl.input.Keyboard;

import com.magiksmostevile.blocks.Alter;
import com.magiksmostevile.blocks.AmethystOre;
import com.magiksmostevile.blocks.SolidAmethyst;
import com.magiksmostevile.entities.amethystSlime.AmethystSlimeEgg;
import com.magiksmostevile.entities.amethystSlime.EntityAmethystSlime;
import com.magiksmostevile.entities.spongie.EntitySpongie;
import com.magiksmostevile.entities.spongie.RenderSpongie;
import com.magiksmostevile.entities.spongie.SpongieEgg;
import com.magiksmostevile.equipment.AmethystAxe;
import com.magiksmostevile.equipment.AmethystBoots;
import com.magiksmostevile.equipment.AmethystChestplate;
import com.magiksmostevile.equipment.AmethystHelmet;
import com.magiksmostevile.equipment.AmethystHoe;
import com.magiksmostevile.equipment.AmethystLeggings;
import com.magiksmostevile.equipment.AmethystPickaxe;
import com.magiksmostevile.equipment.AmethystShovel;
import com.magiksmostevile.equipment.AmethystSword;
import com.magiksmostevile.equipment.DoomStaff.DoomStaff;
import com.magiksmostevile.equipment.EMPStaff.EMPStaff;
import com.magiksmostevile.equipment.EMPStaff.EntityEMP;
import com.magiksmostevile.equipment.EMPStaff.RenderEMP;
import com.magiksmostevile.handler.EvileRecipeHandler;
import com.magiksmostevile.handler.MobDropsHandler;
import com.magiksmostevile.items.Amethyst;
import com.magiksmostevile.items.Doom;
import com.magiksmostevile.items.EMP;
import com.magiksmostevile.items.GreaterPowerStone;
import com.magiksmostevile.items.LesserPowerStone;
import com.magiksmostevile.items.NullItem;
import com.magiksmostevile.items.PoweredAmethyst;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "magiksmostevile";
    public static final String VERSION = "1.0";
    public static final String NAME = "MagiksMostEvile";
    
    public static Item amethyst;
    public static Block amethystOre;
    
    public static ToolMaterial enumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 2, 700, 10.0F, 2.5F, 50); // name, harvestLevel, maxUses, efficiency, damage, enchantability
    
    @Mod.Instance("main")
    public static Main instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpongie.class, 
    		      new RenderSpongie());
    	int spongieId = EntityRegistry.findGlobalUniqueEntityId();
    	EntityRegistry.registerModEntity(EntitySpongie.class, "spongie", spongieId, "magiksmostevile", 16, 1, true);
    	
    	int ameythstSlimeId = EntityRegistry.findGlobalUniqueEntityId();
    	EntityRegistry.registerModEntity(EntityAmethystSlime.class, "amethystSlime", ameythstSlimeId, "magiksmostevile", 64, 1, true);
    	
    	EntityRegistry.registerModEntity(EntityEMP.class, "EMP", 0, this, 64, 10, true);
    	RenderingRegistry.registerEntityRenderingHandler(EntityEMP.class, new RenderEMP());
    	
    	Keybinds.register();
    	 
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    	
    	Item amethyst = new Amethyst("amethyst");
    	GameRegistry.registerItem(amethyst, "amethyst");
    	
    	Item poweredAmethyst = new PoweredAmethyst("poweredAmethyst");
    	GameRegistry.registerItem(poweredAmethyst, "poweredAmethyst");
    	
    	Item lesserPowerStone = new LesserPowerStone("lesserPowerStone");
    	GameRegistry.registerItem(lesserPowerStone, "lesserPowerStone");     
    	
    	Item greaterPowerStone = new GreaterPowerStone("greaterPowerStone");
    	GameRegistry.registerItem(greaterPowerStone, "greaterPowerStone");
    	
    	Item amethystSword = new AmethystSword("amethystSword", enumToolMaterialAmethyst);
    	GameRegistry.registerItem(amethystSword, "amethystSword");
    	
    	Item amethystPickaxe = new AmethystPickaxe("amethystPickaxe", enumToolMaterialAmethyst);
    	GameRegistry.registerItem(amethystPickaxe, "amethystPickaxe");
    	
    	Item amethystAxe = new AmethystAxe("amethystAxe", enumToolMaterialAmethyst);
    	GameRegistry.registerItem(amethystAxe, "amethystAxe");
    	
    	Item amethystHoe = new AmethystHoe("amethystHoe", enumToolMaterialAmethyst);
    	GameRegistry.registerItem(amethystHoe, "amethystHoe");
    	
    	Item amethystShovel = new AmethystShovel("amethystShovel", enumToolMaterialAmethyst);
    	GameRegistry.registerItem(amethystShovel, "amethystShovel");
    	    	
    	Item amethystHelmet = new AmethystHelmet("amethystHelmet");
    	GameRegistry.registerItem(amethystHelmet, "amethystHelmet");
    	    	
    	Item amethystChestplate = new AmethystChestplate("amethystChestplate");
    	GameRegistry.registerItem(amethystChestplate, "amethystChestplate");
    	    	
    	Item amethystLeggings = new AmethystLeggings("amethystLeggings");
    	GameRegistry.registerItem(amethystLeggings, "amethystLeggings");
    	    	
    	Item amethystBoots = new AmethystBoots("amethystBoots");
    	GameRegistry.registerItem(amethystBoots, "amethystBoots");
    	
    	Item emp = new EMP("emp");
    	GameRegistry.registerItem(emp, "emp");
    	
    	Item empStaff = new EMPStaff("empStaff");
    	GameRegistry.registerItem(empStaff, "empStaff");
    	
    	Item doom = new Doom("doom");
    	GameRegistry.registerItem(doom, "doom");
    	
    	Item doomStaff = new DoomStaff("doomStaff");
    	GameRegistry.registerItem(doomStaff, "doomStaff");
    	
    	SpongieEgg spongieEgg = new SpongieEgg();
    	spongieEgg.registerSpawnEgg("spongie", 0xeeff00, 0x000000);
    	
    	AmethystSlimeEgg amethystSlimeEgg = new AmethystSlimeEgg();
    	amethystSlimeEgg.registerSpawnEgg("amethystSlime", 0xb600ff, 0xfa00ff);
    	
    	Item nullItem = new NullItem("nullItem");
    	GameRegistry.registerItem(nullItem, "nullItem");
    	
    	
    	
    	
    	
    	
    	
    	Block amethystOre = new AmethystOre(Material.rock, "amethystOre", amethyst);
    	GameRegistry.registerBlock(amethystOre, "amethystOre");
    	
    	EvileLog.LogText(true, 1, ("ID for amethystOre: " + GameRegistry.findUniqueIdentifierFor(amethystOre)));

    	Block solidAmethyst = new SolidAmethyst(Material.rock, "solidAmethyst");
    	GameRegistry.registerBlock(solidAmethyst, "solidAmethyst");
    	
    	Block alter = new Alter(Material.rock, "alter");
    	GameRegistry.registerBlock(alter, "alter");
    	    	
    	
    	EvileRecipeHandler.init(amethyst, solidAmethyst, alter, lesserPowerStone, Blocks.end_stone, Items.stick, amethystSword, amethystPickaxe, amethystAxe, amethystHoe, amethystShovel); //THIS MUST BE AFTER ALL BLOCKS AND ITEMS HAVE BEEN INITIALISED
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	EvileStructureGenerator generator = new EvileStructureGenerator();
    	GameRegistry.registerWorldGenerator(generator, 3);
    	
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
    	new KeyBinding("key.wisdom", Keyboard.KEY_V, "key.categories.magiksmostevile");
    }
 
}







