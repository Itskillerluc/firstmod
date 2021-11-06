package com.itskillerluc.firstmod.tileentity;

import com.itskillerluc.firstmod.block.ModBlocks;
import com.itskillerluc.firstmod.firstmod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, firstmod.MOD_ID);

    public static RegistryObject<TileEntityType<LightningChargerTile>> LIGHTNING_CHARGER_TILE = TILE_ENTITIES.register("lightning_charger_tile", () -> TileEntityType.Builder.of(LightningChargerTile::new, ModBlocks.LIGHTNING_CHARGER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }

}
