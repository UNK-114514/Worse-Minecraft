package com.unk.wmc.screen.custom;

import com.unk.wmc.Wmc;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import com.unk.wmc.menu.custom.SmithingTemplateCraftingMenu;
import org.jetbrains.annotations.NotNull;

public class SmithingTemplateCraftingScreen extends ItemCombinerScreen<SmithingTemplateCraftingMenu> {
    private static final ResourceLocation ERROR_SPRITE = ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "container/smithing_template_crafting/error");

    public SmithingTemplateCraftingScreen(SmithingTemplateCraftingMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "textures/gui/container/smithing_template_crafting.png"));
        this.titleLabelX = 44;
        this.titleLabelY = 15;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
    }

    @Override
    protected void renderErrorIcon(@NotNull GuiGraphics guiGraphics, int x, int y) {
        if (this.menu.selectedRecipe == null) {
            guiGraphics.blitSprite(ERROR_SPRITE, x + 65, y + 46, 28, 21);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
