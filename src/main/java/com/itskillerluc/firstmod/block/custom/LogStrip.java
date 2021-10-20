package com.itskillerluc.firstmod.block.custom;

import com.itskillerluc.firstmod.block.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.permission.context.IContext;


import javax.annotation.Nullable;

public class LogStrip extends RotatedPillarBlock {
    //public static final Direction.Axis AXIS = BlockStateProperties.AXIS;
    public LogStrip(Properties prop) {
        super(prop);
    }





    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {

        if (toolType == ToolType.AXE && state.getBlock().defaultBlockState() == ModBlocks.MAGICAL_LOG.get().defaultBlockState()) return ModBlocks.STRIPPED_MAGICAL_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        else if (toolType == ToolType.AXE && state.getBlock().defaultBlockState() == ModBlocks.MAGICAL_WOOD.get().defaultBlockState()) return ModBlocks.STRIPPED_MAGICAL_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        else return null;
    }
}
