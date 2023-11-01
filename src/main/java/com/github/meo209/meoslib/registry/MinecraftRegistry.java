package com.github.meo209.meoslib.registry;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.event.GameEvent;

public enum MinecraftRegistry {

    GAME_EVENT(GameEvent.class),
    SOUND_EVENT(SoundEvent.class),
    FLUID(Fluid.class),
    STATUS_EFFECT(StatusEffect.class),
    BLOCK(Block.class),
    ENCHANTMENT(Enchantment.class),
    ENTITY_TYPE(EntityType.class),
    ITEM(Item.class),
    POTION(Potion.class);

    public final Class<?> clazz;

    MinecraftRegistry(Class<?> clazz) {
        this.clazz = clazz;
    }
}
