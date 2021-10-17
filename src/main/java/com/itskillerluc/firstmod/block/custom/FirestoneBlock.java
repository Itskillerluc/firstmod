package com.itskillerluc.firstmod.block.custom;

import com.itskillerluc.firstmod.item.custom.Firestone;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;


public class FirestoneBlock extends Block {
    private boolean onByRedstone = false;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public FirestoneBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide()){
            BlockPos position_ = pos.relative(Direction.UP);
            if (worldIn.getBlockState(position_).isAir(worldIn, position_)) {
                worldIn.playSound(player, position_, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.random.nextFloat() * 0.4F + 0.8F);
                BlockState blockstate_ = AbstractFireBlock.getState(worldIn, position_);

                worldIn.setBlock(position_, blockstate_, 11);

            }
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block block, BlockPos bpos, boolean bool) {
        if (!worldIn.isClientSide) {
            boolean flag = worldIn.hasNeighborSignal(pos);
            BlockPos position = pos.relative(Direction.UP);
            if(flag){
                if (worldIn.getBlockState(position).isAir(worldIn, position)) {
                    BlockState blockstate = AbstractFireBlock.getState(worldIn, pos);
                    onByRedstone = true;
                    worldIn.setBlock(position, blockstate, 11);

                }
            }else if(!flag && worldIn.getBlockState(position).is(Blocks.FIRE) && onByRedstone == true){
                worldIn.setBlock(position, Blocks.AIR.defaultBlockState(), 3);
                onByRedstone = false;
            }
        }
        super.neighborChanged(state, worldIn, pos, block, bpos, bool);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if(!worldIn.isClientSide()){
            ItemStack itemstack = player.getItemInHand(Hand.MAIN_HAND);
            Item item = itemstack.getItem();
            if (item != Items.NETHERITE_PICKAXE) {
                Firestone.lightEntityOnFire(player, 20);
            }
        }
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        Firestone.lightEntityOnFire(entityIn, 5);
        super.stepOn(worldIn, pos, entityIn);
    }
}
