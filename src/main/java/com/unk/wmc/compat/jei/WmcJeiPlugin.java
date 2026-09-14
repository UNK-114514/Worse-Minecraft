package com.unk.wmc.compat.jei;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.compat.jei.category.AltarCraftingRecipeCategory;
import com.unk.wmc.compat.jei.replacer.SmitingTemplateRecipeMaker;
import com.unk.wmc.compat.jei.subtype.DataComponentsSubtypeInterpreter;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
@SuppressWarnings("unused")
public class WmcJeiPlugin implements IModPlugin {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "wmc_jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new AltarCraftingRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        RecipeManager manager = level.getRecipeManager();

        List<AltarCraftingRecipe> altarCraftingRecipes = getAllRecipes(WmcRecipeTypes.ALTAR_CRAFTING.get(), manager);
        registration.addRecipes(WmcJeiRecipeTypes.ALTAR_CRAFTING, altarCraftingRecipes);

        registration.addRecipes(RecipeTypes.CRAFTING, SmitingTemplateRecipeMaker.createRecipes(registration.getJeiHelpers()));
    }

    private static <I extends RecipeInput, T extends Recipe<I>> List<T> getAllRecipes(
            RecipeType<T> recipeType, RecipeManager manager) {
        List<RecipeHolder<T>> holders = manager.getAllRecipesFor(recipeType);
        List<T> recipes = new ArrayList<>();
        holders.forEach((holder) -> recipes.add(holder.value()));

        return recipes;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(
                VanillaTypes.ITEM_STACK,
                WmcItems.SMITHING_TEMPLATE_DUST.get(),
                new DataComponentsSubtypeInterpreter(
                        WmcDataComponentTypes.SIMPLE_ITEM.get(),
                        WmcDataComponentTypes.ACTIVATABLE.get()
                )
        );

        registration.registerSubtypeInterpreter(
                VanillaTypes.ITEM_STACK,
                WmcItems.SMITHING_TEMPLATE_CORE.get(),
                new DataComponentsSubtypeInterpreter(
                        WmcDataComponentTypes.SIMPLE_ITEM.get()
                )
        );
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(WmcBlocks.ACTIVATE_ALTAR, WmcJeiRecipeTypes.ALTAR_CRAFTING);
        registration.addRecipeCatalyst(WmcBlocks.ACTIVATE_PEDESTAL, WmcJeiRecipeTypes.ALTAR_CRAFTING);
    }
}
