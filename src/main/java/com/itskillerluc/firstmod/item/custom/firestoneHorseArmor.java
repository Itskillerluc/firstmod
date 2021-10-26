package com.itskillerluc.firstmod.item.custom;

import net.minecraft.entity.MobEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class firestoneHorseArmor extends HorseArmorItem {

    public firestoneHorseArmor(int pProtection, String pIdentifier, Properties pProperties) {
        super(pProtection, pIdentifier, pProperties);
    }

    public firestoneHorseArmor(int pProtection, ResourceLocation texture, Properties pProperties) {
        super(pProtection, texture, pProperties);
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, World world, MobEntity horse) {
        horse.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 21, 3));

    }
}
