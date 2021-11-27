package com.itskillerluc.firstmod.block.custom;

import com.itskillerluc.firstmod.tileentity.LightningChargerTile;
import com.itskillerluc.firstmod.tileentity.ModTileEntities;
import com.itskillerluc.inventory.container.LightningChargerContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class LightningChargerBlock extends Block {

    public LightningChargerBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if(!pLevel.isClientSide()){
            TileEntity tileEntity = pLevel.getBlockEntity(pPos);

            if(!pPlayer.isCrouching()){
                if (tileEntity instanceof LightningChargerTile){
                    INamedContainerProvider containerProvider = createContainerProvider(pLevel, pPos);

                    NetworkHooks.openGui(((ServerPlayerEntity) pPlayer), containerProvider, tileEntity.getBlockPos());
                }else{
                    throw new IllegalStateException("ContainerProvider Missing!");
                }
            }else{
                if (tileEntity instanceof LightningChargerTile){
                    if (pLevel.isThundering()) {
                        EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) pLevel), null, pPlayer, pPos, SpawnReason.TRIGGERED, true, true);
                        ((LightningChargerTile) tileEntity).lightningStrike();
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World pLevel, BlockPos pPos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.firstmod.lightning_charger");
            }

            @Nullable
            @Override
            public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                return new LightningChargerContainer(p_createMenu_1_, pLevel, pPos, p_createMenu_2_, p_createMenu_3_);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.LIGHTNING_CHARGER_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
