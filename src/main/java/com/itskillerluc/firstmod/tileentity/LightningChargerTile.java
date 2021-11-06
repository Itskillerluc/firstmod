package com.itskillerluc.firstmod.tileentity;

import com.itskillerluc.firstmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LightningChargerTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public LightningChargerTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public LightningChargerTile() {
        this(ModTileEntities.LIGHTNING_CHARGER_TILE.get());
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        itemHandler.deserializeNBT(serializeNBT().getCompound("inv"));
        super.load(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT save(CompoundNBT pCompound) {
        pCompound.put("inv", itemHandler.serializeNBT());
        return super.save(pCompound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch(slot){
                    case 0: return stack.getItem() == ModItems.CHARGED_CRYSTAL.get() || stack.getItem() == Items.GLASS_PANE;
                    case 1: return stack.getItem() == ModItems.CRYSTAL.get() || stack.getItem() == ModItems.FIRESTONE.get();
                    case 2: return stack.getItem() == ModItems.MAGIC_DUST.get();
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                switch(slot){
                    case 0: return 1;
                    default:
                        return 64;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)){
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void lightningStrike(){
        boolean focusInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0 && this.itemHandler.getStackInSlot(0).getItem() == Items.GLASS_PANE;
        boolean crystalInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0 && this.itemHandler.getStackInSlot(0).getItem() == ModItems.CHARGED_CRYSTAL.get();
        boolean crystalInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0 && this.itemHandler.getStackInSlot(1).getItem() == ModItems.CRYSTAL.get();
        boolean magicDustInThirdSlot = this.itemHandler.getStackInSlot(2).getCount() > 0 && this.itemHandler.getStackInSlot(2).getItem() == ModItems.MAGIC_DUST.get();

        if (focusInFirstSlot && crystalInSecondSlot && magicDustInThirdSlot){
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.getStackInSlot(2).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CHARGED_CRYSTAL.get()), false);
        }
    }
}
