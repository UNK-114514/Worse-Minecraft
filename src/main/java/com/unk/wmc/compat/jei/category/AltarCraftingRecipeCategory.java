package com.unk.wmc.compat.jei.category;

import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.compat.jei.WmcJeiRecipeTypes;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AltarCraftingRecipeCategory extends AbstractRecipeCategory<AltarCraftingRecipe> {
    public AltarCraftingRecipeCategory(IGuiHelper guiHelper) {
        super(
                WmcJeiRecipeTypes.ALTAR_RECIPE_TYPE,
                Component.literal("Altar Crafting"),
                guiHelper.createDrawableIngredient(
                        VanillaTypes.ITEM_STACK,
                        WmcBlocks.ACTIVATE_ALTAR.toStack()
                ),
                160,
                140
        );
    }

    @Override
    public void setRecipe(
            @NotNull IRecipeLayoutBuilder builder,
            @NotNull AltarCraftingRecipe recipe, @NotNull IFocusGroup focuses) {
        int centerX = 50;
        int centerY = 50;

        int r = 50;

        List<ItemStack> recipeInputs = List.of(
                recipe.n(), recipe.en(), recipe.e(), recipe.es(),
                recipe.s(), recipe.ws(), recipe.w(), recipe.wn()
        );

        for (int i = 0; i < 8; i++) {
            double alpha = Math.PI / 4 * i;

            int slotX = (int) (Math.sin(alpha) * r) + centerX;
            int slotY = (int) (Math.cos(alpha) * r) + centerY;

            builder.addInputSlot(slotX, slotY).addItemStack(recipeInputs.get(i));
        }

        builder.addInputSlot(centerX, centerY).addItemStack(recipe.mid());

        builder.addOutputSlot(140, 57).addItemStack(recipe.result());
    }

    @Override
    public void draw(
            @NotNull AltarCraftingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
            @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int centerX = 46;
        int centerY = 65;

        int r = 50;

        for (int i = 0; i < 8; i++) {
            double alpha = Math.PI / 4 * i;

            int iconX = (int) (Math.sin(alpha) * r) + centerX;
            int iconY = (int) (Math.cos(alpha) * r) + centerY;

            drawItem(guiGraphics, WmcBlocks.ACTIVATE_PEDESTAL.toStack(), iconX, iconY, 1.5F);
        }

        drawItem(guiGraphics, WmcBlocks.ACTIVATE_ALTAR.toStack(), centerX, centerY, 1.5F);
    }

    public void drawItem(GuiGraphics guiGraphics, ItemStack stack, int xOffset, int yOffset, float scale) {
        var poseStack = guiGraphics.pose();
        poseStack.pushPose();

        poseStack.translate(xOffset, yOffset, 0);
        poseStack.scale(scale, scale, scale);
        guiGraphics.renderItem(stack, 0, 0);

        poseStack.popPose();
    }
}
