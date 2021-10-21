package com.itskillerluc.firstmod.world;

import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.world.gen.ModTreeGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = firstmod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModTreeGen.genTrees(event);
    }

}
