package com.itskillerluc.firstmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.concurrent.TimeUnit;


public class Slipper extends Item {
    public Slipper(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity p_77644_2_, LivingEntity p_77644_3_) {
        World world = p_77644_3_.level;
        BlockPos EnemyPos = p_77644_2_.blockPosition();
        if(!world.isClientSide) {
            for(int i = 0; i < 50; ++i)
                EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) world), null, null, EnemyPos, SpawnReason.TRIGGERED, true, true);

            p_77644_2_.kill();
            p_77644_1_.hurtAndBreak(1, p_77644_3_, player -> player.broadcastBreakEvent(p_77644_3_.getUsedItemHand()));
        }
        return super.hurtEnemy(p_77644_1_, p_77644_2_, p_77644_3_);
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }
}
