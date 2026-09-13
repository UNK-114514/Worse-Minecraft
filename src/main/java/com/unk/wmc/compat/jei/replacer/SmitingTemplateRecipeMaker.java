package com.unk.wmc.compat.jei.replacer;

import com.unk.wmc.Wmc;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.WmcItems;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;

import java.util.ArrayList;
import java.util.List;

public class SmitingTemplateRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes(IJeiHelpers jeiHelpers) {
        IVanillaRecipeFactory vanillaRecipeFactory = jeiHelpers.getVanillaRecipeFactory();

        String group = "jei.smithing_dust";

        List<RecipeHolder<CraftingRecipe>> recipeHolders = new ArrayList<>();

        if (BuiltInRegistries.ITEM.getTag(WmcItemTags.SMITHING_TEMPLATES).isEmpty()) return recipeHolders;

        for (Holder<Item> itemHolder : BuiltInRegistries.ITEM.getTag(WmcItemTags.SMITHING_TEMPLATES).get()) {
            Item item = itemHolder.value();

            ItemStack dust = WmcItems.SMITHING_TEMPLATE_DUST.get().getDefaultInstance();
            dust.set(WmcDataComponentTypes.SIMPLE_ITEM, new SimpleItemData(BuiltInRegistries.ITEM.getKey(item)));

            Ingredient dustIngredient = DataComponentIngredient.of(false, dust);
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "jei.smithing_dust." + item.getDescriptionId());

            CraftingRecipe recipe = vanillaRecipeFactory.createShapedRecipeBuilder(CraftingBookCategory.MISC, List.of(item.getDefaultInstance()))
                    .group(group)
                    .pattern("ada")
                    .pattern("aea")
                    .pattern("aaa")
                    .define('a', Ingredient.of(Blocks.DIAMOND_BLOCK))
                    .define('d', dustIngredient)
                    .define('e', Ingredient.of(WmcItems.EMPTY_SMITING_TEMPLATE))
                    .build();
            recipeHolders.add(new RecipeHolder<>(id, recipe));
        }

        return recipeHolders;
    }
}
