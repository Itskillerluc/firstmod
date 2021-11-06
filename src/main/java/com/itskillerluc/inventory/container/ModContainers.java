package com.itskillerluc.inventory.container;

import com.itskillerluc.firstmod.firstmod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, firstmod.MOD_ID);

    public static final RegistryObject<ContainerType<LightningChargerContainer>> LIGHTNING_CHARGER_CONTAINER = CONTAINERS.register("lightning_charger_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getCommandSenderWorld();
        return new LightningChargerContainer(windowId, world, pos, inv, inv.player);
    }));

    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
