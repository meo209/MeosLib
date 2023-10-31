package io.github.meo209.meoslib.registry;

import net.minecraft.item.Item;

public enum MinecraftRegistry {

    ITEM(Item.class);

    public final Class<?> clazz;

    MinecraftRegistry(Class<?> clazz) {
        this.clazz = clazz;
    }
}
