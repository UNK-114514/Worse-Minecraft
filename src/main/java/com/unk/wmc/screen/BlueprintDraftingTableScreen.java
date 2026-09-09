package com.unk.wmc.screen;

import com.unk.wmc.Wmc;
import com.unk.wmc.menu.AssemblyTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BlueprintDraftingTableScreen extends ItemCombinerScreen<AssemblyTableMenu> {
    public static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "textures/gui/container/blueprint_drafter.png");

    public static final ResourceLocation ERROR =
            ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "container/general/error");

    public BlueprintDraftingTableScreen(AssemblyTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
    }

    @Override
    protected void renderErrorIcon(@NotNull GuiGraphics guiGraphics, int x, int y) {
        if (this.hasRecipeError()) {
            guiGraphics.blitSprite(ERROR, x + 99, y + 30, 28, 21);
        }
    }

    private boolean hasRecipeError() {
        return (this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem())
                && !this.menu.getSlot(2).hasItem();
    }
}
