package com.itskillerluc.firstmod.world.gen;

import com.itskillerluc.firstmod.block.custom.trees.MagicalTree;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGen {

    public static void genTrees(final BiomeLoadingEvent event){

        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        System.out.println(key);
        if(types.contains(BiomeDictionary.Type.FOREST)){
            List<Supplier<ConfiguredFeature<?, ?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(() -> ModConfiguredFeatures.MAGICAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.CHANCE.configured(new ChanceConfig(200))));
        }
    }

}
