package com.github.meo209.meoslib.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public enum MinecraftRegistry {

    ITEM(Item.class),
    BLOCK(Block.class),
    FLUID(Fluid.class);

    public final Class<?> clazz;

    MinecraftRegistry(Class<?> clazz) {
        this.clazz = clazz;
    }
}
