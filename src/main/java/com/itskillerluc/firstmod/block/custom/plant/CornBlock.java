package com.itskillerluc.firstmod.block.custom.plant;

import com.itskillerluc.firstmod.block.ModBlocks;
import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

import java.util.EnumMap;
import java.util.Random;
import java.util.logging.Level;

public class CornBlock extends BushBlock implements IGrowable {


    public final static EnumProperty<EnumCornGrowth> GROWTH = EnumProperty.create("growth", EnumCornGrowth.class);

    public CornBlock(Properties builder)
    {
        super(builder);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        super.createBlockStateDefinition(p_206840_1_);
        p_206840_1_.add(GROWTH);
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        boolean b = super.canSurvive(p_196260_1_, p_196260_2_, p_196260_3_);
        if(p_196260_1_.getValue(GROWTH)==EnumCornGrowth.TOP0)
        {
            BlockState stateBelow = p_196260_2_.getBlockState(p_196260_3_.below());
            b = stateBelow.getBlock().equals(this)&&stateBelow.getValue(GROWTH)==EnumCornGrowth.BOTTOM0.getMax();
        }
        return b;
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.getBlock()==Blocks.FARMLAND;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.CROP;
    }

    private static final EnumMap<EnumCornGrowth, VoxelShape> shapes = new EnumMap<>(EnumCornGrowth.class);

    static
    {
        shapes.put(EnumCornGrowth.BOTTOM0, VoxelShapes.create(
                new AxisAlignedBB(0, 0, 0, 1, .375f, 1)));
        shapes.put(EnumCornGrowth.BOTTOM1, VoxelShapes.create(
                new AxisAlignedBB(0, 0, 0, 1, .625f, 1)));
        shapes.put(EnumCornGrowth.BOTTOM2, VoxelShapes.create(
                new AxisAlignedBB(0, 0, 0, 1, .875f, 1)));
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return shapes.getOrDefault(p_220053_1_.getValue(GROWTH), VoxelShapes.block());
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, world, pos, neighbor);

        if(world.getBlockState(pos).getValue(GROWTH)!=EnumCornGrowth.TOP0)

            if(world instanceof World){
                ((World)world).updateNeighborsAt(pos.offset(0, 1, 0), this);
            }
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        int light = p_225534_2_.getMaxLocalRawBrightness(p_225534_3_);
        if(light >= 12)
        {
            EnumCornGrowth growth = p_225534_1_.getValue(GROWTH);
            if(growth==EnumCornGrowth.TOP0)
                return;
            float speed = getGrowthSpeed(p_225534_2_, p_225534_3_, p_225534_1_, light);
            if(p_225534_4_.nextInt((int)(50F/speed)+1)==0)
            {
                if(growth.getMax()!=growth)
                    p_225534_2_.setBlockAndUpdate(p_225534_3_, p_225534_1_.setValue(GROWTH, growth.next()));
                else if(p_225534_2_.isEmptyBlock(p_225534_3_.offset(0, 1, 0)))
                    p_225534_2_.setBlockAndUpdate(p_225534_3_.offset(0, 1, 0), p_225534_1_.setValue(GROWTH, EnumCornGrowth.TOP0));
            }
        }
    }

    private float getGrowthSpeed(ServerWorld world, BlockPos pos, BlockState state, int light)
    {
        float growth = 0.125f*(light-11);
        if(world.canSeeSkyFromBelowWater(pos))
            growth += 2f;
        BlockState soil = world.getBlockState(pos.offset(0, -1, 0));
        if(soil.getBlock().isFertile(soil, world, pos.offset(0, -1, 0)))
            growth *= 1.5f;
        return 1f+growth;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        EnumCornGrowth growth = pState.getValue(GROWTH);
        if(growth!=growth.getMax())
            return true;
        else
            return growth==EnumCornGrowth.BOTTOM2&&pLevel.getBlockState(pPos.offset(0, 1, 0)).getBlock()!=this;
    }

    //canBonemeal


    @Override
    public boolean isBonemealSuccess(World pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return isValidBonemealTarget(pLevel, pPos, pLevel.getBlockState(pPos), pLevel.isClientSide);
    }

    @Override
    public void performBonemeal(ServerWorld pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        EnumCornGrowth growth = pState.getValue(GROWTH);
        if(growth!=growth.getMax())
        {
            int span = growth.getMax().ordinal()-growth.ordinal();
            EnumCornGrowth newGrowth = growth;
            int growBy = RANDOM.nextInt(span)+1;
            for(int i = 0; i < growBy; ++i)
                newGrowth = newGrowth.next();
            pLevel.setBlockAndUpdate(pPos, pState.setValue(GROWTH, newGrowth));
            growth = newGrowth;
        }
        if(growth==EnumCornGrowth.BOTTOM2&&pLevel.isEmptyBlock(pPos.offset(0, 1, 0)))
            pLevel.setBlockAndUpdate(pPos.offset(0, 1, 0), pState.setValue(GROWTH, EnumCornGrowth.TOP0));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        //after item do this thingy
        return new ItemStack(ModItems.CORN_SEEDS.get());

    }
}
