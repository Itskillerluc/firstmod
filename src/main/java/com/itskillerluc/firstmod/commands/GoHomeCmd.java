package com.itskillerluc.firstmod.commands;

import com.itskillerluc.firstmod.firstmod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class GoHomeCmd {
    public GoHomeCmd(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("home").executes((command -> {
            return goHome(command.getSource());
        })));
    }

    private int goHome(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        boolean hasHomePos = player.getPersistentData().getIntArray(firstmod.MOD_ID + "homepos").length != 0;

        if (hasHomePos){
            int [] playerPos = player.getPersistentData().getIntArray(firstmod.MOD_ID + "homepos");
            player.teleportTo(playerPos[0], playerPos[1], playerPos[2]);

            source.sendSuccess(new StringTextComponent("Welcome home " + player.getDisplayName().getString()), true);
            return 1;
        } else{
            source.sendFailure(new StringTextComponent("No home found!"));
            return -1;
        }
    }
}
