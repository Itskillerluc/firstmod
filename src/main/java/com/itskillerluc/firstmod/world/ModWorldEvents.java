package com.itskillerluc.firstmod.world;

import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.world.gen.ModFlowerGen;
import com.itskillerluc.firstmod.world.gen.ModOreGen;
import com.itskillerluc.firstmod.world.gen.ModTreeGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = firstmod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModOreGen.biomeModification(event);
        ModFlowerGen.genFlowers(event);
        ModTreeGen.genTrees(event);
    }
}
