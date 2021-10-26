package com.itskillerluc.firstmod.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RefineryKit extends Item {
    public RefineryKit(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.hurt(1, random, null)){
            return ItemStack.EMPTY;
        }else{
            return container;
        }

    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
