package com.magiksmostevile.handler;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	public static enum EnumType implements IStringSerializable {

		AMETHYST(0, "amethyst"), LEAD(1, "lead");

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name, unlocalisedName;

		private EnumType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalisedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalisedName = unlocalisedName;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getUnlocalisedName() {
			return this.unlocalisedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumType byMetadata(int data) {
			return META_LOOKUP[data];
		}

		static {
			for (EnumType enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
