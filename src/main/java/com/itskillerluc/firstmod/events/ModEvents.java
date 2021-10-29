package com.itskillerluc.firstmod.events;

import com.itskillerluc.firstmod.commands.GoHomeCmd;
import com.itskillerluc.firstmod.commands.SetHomeCmd;
import com.itskillerluc.firstmod.firstmod;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = firstmod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCmdRegister(RegisterCommandsEvent event) {
        new SetHomeCmd(event.getDispatcher());
        new GoHomeCmd(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if(!event.getOriginal().getCommandSenderWorld().isClientSide()) {
            event.getPlayer().getPersistentData().putIntArray(firstmod.MOD_ID + "homepos", event.getOriginal().getPersistentData().getIntArray(firstmod.MOD_ID + "homepos"));
        }
    }
}
