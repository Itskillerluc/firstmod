package com.itskillerluc.firstmod.world.gen;

import com.itskillerluc.firstmod.block.ModBlocks;
import com.itskillerluc.firstmod.firstmod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModOreGen {
    public static ConfiguredFeature<?,?> CONFIGURED_CRYSTAL_ORE;

    // register to the Mod event bus
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CONFIGURED_CRYSTAL_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CRYSTAL_ORE.get().defaultBlockState(), 17)).range(128).squared().count(20);

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(firstmod.MOD_ID, "crystal_ore"), CONFIGURED_CRYSTAL_ORE);
        });
    }

    // register to the Forge event bus
    public static void biomeModification(final BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> CONFIGURED_CRYSTAL_ORE);
    }
}
