package com.itskillerluc.firstmod.screen;

import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.inventory.container.LightningChargerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LightningChargerScrn extends ContainerScreen<LightningChargerContainer> {

    private final ResourceLocation GUI = new ResourceLocation(firstmod.MOD_ID, "textures/gui/lightning_charger_gui.png");

    public LightningChargerScrn(LightningChargerContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(pMatrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());

        if (menu.isLightningstorm()){
            this.blit(pMatrixStack, i + 82, j + 9, 176, 0, 13, 17);
        }
    }
}
