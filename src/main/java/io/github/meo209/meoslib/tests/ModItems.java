package io.github.meo209.meoslib.tests;

import io.github.meo209.meoslib.registry.MinecraftRegistry;
import io.github.meo209.meoslib.registry.ModRegistry;
import io.github.meo209.meoslib.registry.Named;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@ModRegistry(MinecraftRegistry.ITEM)
public class ModItems {

    @Named("minecraft_custom_item")
    public static final Item TEST_ITEM = new Item(new FabricItemSettings());

    public static final Block NOT_SO_ITEM = new Block(FabricBlockSettings.create());

}
