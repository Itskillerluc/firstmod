package com.itskillerluc.firstmod.world.gen;

import com.itskillerluc.firstmod.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGICAL_TREE = register("magicaltree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MAGICAL_LOG.get().defaultBlockState()), new SimpleBlockStateProvider(ModBlocks.MAGICAL_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 6), new StraightTrunkPlacer(6, 5, 3), new TwoLayerFeature(2, 0, 0))).ignoreVines().build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String pId, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, pId, pConfiguredFeature);
    }
}
