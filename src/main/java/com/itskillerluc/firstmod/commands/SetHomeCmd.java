package com.itskillerluc.firstmod.commands;

import com.itskillerluc.firstmod.firstmod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class SetHomeCmd {
    public SetHomeCmd(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("sethome").executes((command -> {
            return setHome(command.getSource());
        })));
    }

    private int setHome(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        BlockPos playerPos = player.blockPosition();
        String pos = "[" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + "]";

        player.getPersistentData().putIntArray(firstmod.MOD_ID + "homepos", new int[] {playerPos.getX(), playerPos.getY(), playerPos.getZ()});

        source.sendSuccess(new StringTextComponent("Home set at: " + pos), true);

        return 1;
    }

}
