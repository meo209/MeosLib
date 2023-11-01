package com.github.meo209.meoslib.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public abstract class AdvancedScreenHandler extends ScreenHandler {

    public final Inventory inventory;
    public final PropertyDelegate propertyDelegate;
    public final BlockEntity blockEntity;

    public final List<Slot> blockSlots = new ArrayList<>();

    public AdvancedScreenHandler(int syncId, ScreenHandlerType<?> type, PlayerInventory playerInventory, BlockEntity blockEntity,
                                 PropertyDelegate propertyDelegate) {
        super(type, syncId);
        //checkSize((Inventory) blockEntity, 3);
        this.inventory = ((Inventory) blockEntity);
        this.inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = blockEntity;

        this.initSlots();

        this.addPlayerInventory(playerInventory);
        this.addPlayerHotbar(playerInventory);

        this.addProperties(this.propertyDelegate);
    }

    public abstract void initSlots();

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public Slot addBlockSlot(Inventory inventory, int x, int y) {
        Slot slot = new Slot(inventory, this.blockSlots.size(), x, y);
        slot.id = this.blockSlots.size();
        this.blockSlots.add(slot);
        this.addSlot(slot);
        return slot;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
