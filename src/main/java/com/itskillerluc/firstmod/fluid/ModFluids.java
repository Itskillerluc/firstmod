package com.itskillerluc.firstmod.fluid;

import com.itskillerluc.firstmod.block.ModBlocks;
import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, firstmod.MOD_ID);

    public static final RegistryObject<FlowingFluid> MILK_FLUID = FLUIDS.register("milk_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.MILK_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MILK_FLOWING = FLUIDS.register("milk_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.MILK_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MILK_PROPERTIES = new ForgeFlowingFluid.Properties(() -> MILK_FLUID.get(), () -> MILK_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).density(25).luminosity(1).viscosity(1).sound(SoundEvents.COW_MILK).overlay(WATER_OVERLAY_RL).color(0xFFFDFAF2)).slopeFindDistance(2).levelDecreasePerBlock(1).block(() -> ModFluids.MILK_BLOCK.get()).bucket(() -> ModItems.BUCKET_MILK.get());
    public static final RegistryObject<FlowingFluidBlock> MILK_BLOCK = ModBlocks.BLOCKS.register("milk", () -> new FlowingFluidBlock(() -> ModFluids.MILK_FLUID.get(), AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
