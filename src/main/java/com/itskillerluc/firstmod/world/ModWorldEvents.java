package com.itskillerluc.firstmod.world;

import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.world.gen.ModOreGen;
import com.itskillerluc.firstmod.world.gen.ModTreeGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = firstmod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModOreGen.biomeModification(event);
        ModTreeGen.genTrees(event);
    }

    public static void oreLoadingEvent(FMLCommonSetupEvent event){
        ModOreGen.commonSetup(event);
    }

}
