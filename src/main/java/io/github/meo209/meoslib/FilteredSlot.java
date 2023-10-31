package io.github.meo209.meoslib;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

import java.util.Arrays;
import java.util.List;

public class FilteredSlot extends Slot {

    public final Item[] filter;

    public FilteredSlot(Inventory inventory, int index, int x, int y, Item... filter) {
        super(inventory, index, x, y);
        this.filter = filter;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return Arrays.stream(filter).anyMatch(item -> item == stack.getItem());
    }

}
