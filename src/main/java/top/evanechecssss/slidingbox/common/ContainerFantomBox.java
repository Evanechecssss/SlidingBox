package top.evanechecssss.slidingbox.common;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class ContainerFantomBox extends Container {
    public TEFantomBlock tileEntity;

    public ContainerFantomBox(IInventory playerInventory, TEFantomBlock te) {
        this.tileEntity = te;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addOwnSlots(){
        this.addSlotToContainer(new SlotItemHandler(tileEntity.itemRender, 0, 70, 19));
    }



    private void addPlayerSlots(IInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10 - 1, x, y));
            }
        }

        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 59 + 83;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }


}