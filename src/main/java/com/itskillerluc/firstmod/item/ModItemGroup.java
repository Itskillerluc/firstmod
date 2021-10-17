package com.itskillerluc.firstmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup FIRSTMODGROUP = new ItemGroup("firstmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CRYSTAL.get());
        }
    };
}
