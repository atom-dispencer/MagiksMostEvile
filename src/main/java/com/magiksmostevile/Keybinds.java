package com.magiksmostevile;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class Keybinds {
	public static KeyBinding wisdom;
	
	public static void register() {
		wisdom = new KeyBinding("key.wisdom", Keyboard.KEY_V, "key.categories.magiksmostevile"); 
		
		ClientRegistry.registerKeyBinding(wisdom);
	}

}
