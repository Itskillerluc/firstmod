package com.itskillerluc.firstmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraft.item.AxeItem;

import javax.annotation.Nullable;

public class magical_log extends Block {
    public magical_log(Properties prop) {
        super(prop);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (toolType == ToolType.AXE){
            System.out.println("STRIP");
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
