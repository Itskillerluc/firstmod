package com.itskillerluc.firstmod.item.custom;

import com.itskillerluc.firstmod.item.ModItems;
import com.itskillerluc.firstmod.util.firstmodTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class Firestone extends Item {
    public Firestone(Properties properties) {
        super(properties);
    }



    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if(!world.isClientSide) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);
            stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(context.getHand()));
      }

      return super.onItemUseFirst(stack, context);
  }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslationTextComponent("tooltip.firstmod.firestone_shift"));

        }else{
            tooltip.add(new TranslationTextComponent("tooltip.firstmod.firestone"));

        }


        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context, PlayerEntity playerEntity) {


        if(random.nextFloat() > 0.6f){
            lightEntityOnFire(playerEntity, 10);
        } else if(!playerEntity.isOnFire() && blockIsValidForResistance(clickedBlock)){
            gainFireResistanceAndDestroyBlock(playerEntity, context.getLevel(), context.getClickedPos());

        } else {
            lightGroundOnFire(context);
        }

  }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.is(firstmodTags.Blocks.FIRESTONE_CLICKABLE_BLOCKS);
    }

    public static void lightEntityOnFire(Entity entity, int seconds) {
        entity.setSecondsOnFire(seconds);
    }

    private void gainFireResistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity playerEntity) {
        playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000));
    }

    public static void lightGroundOnFire(ItemUseContext context){
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());

        if (AbstractFireBlock.canBePlacedAt(world, blockpos, context.getHorizontalDirection())) {
            world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate = AbstractFireBlock.getState(world, blockpos);

            world.setBlock(blockpos, blockstate, 11);
        }
    }
}
