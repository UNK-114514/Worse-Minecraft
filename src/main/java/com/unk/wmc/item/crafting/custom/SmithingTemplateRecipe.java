package com.unk.wmc.item.crafting.custom;

import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.helper.RecipeHelper;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmithingTemplateRecipe extends CustomRecipe {
    public SmithingTemplateRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(@NotNull CraftingInput craftingInput, @NotNull Level level) {
        if (craftingInput.width() != 3 || craftingInput.height() != 3) return false;

        List<List<Item>> items =
                List.of(
                        List.of(
                                Items.DIAMOND_BLOCK,
                                WmcItems.SMITHING_TEMPLATE_DUST.get(),
                                Items.DIAMOND_BLOCK
                        ),
                        List.of(
                                Items.DIAMOND_BLOCK,
                                WmcItems.EMPTY_SMITING_TEMPLATE.get(),
                                Items.DIAMOND_BLOCK
                        ),
                        List.of(
                                Items.DIAMOND_BLOCK,
                                Items.DIAMOND_BLOCK,
                                Items.DIAMOND_BLOCK
                        )
                );

        return RecipeHelper.matchAll(craftingInput, items, 3, 3);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        ItemStack dustItemStack = craftingInput.getItem(1, 0);
        SimpleItemData data = dustItemStack.get(WmcDataComponentTypes.SIMPLE_ITEM);

        if (!dustItemStack.is(WmcItems.SMITHING_TEMPLATE_DUST)) return ItemStack.EMPTY;
        if (data == null) return ItemStack.EMPTY;

        Item result = ItemStackHelper.fromResourceLocation(data.itemId());
        if (result == null) return ItemStack.EMPTY;
        if (!result.getDefaultInstance().is(WmcItemTags.SMITHING_TEMPLATES)) return ItemStack.EMPTY;

        return result.getDefaultInstance();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WmcRecipeSerializers.SMITHING_TEMPLATE_RECIPE_SERIALIZER.get();
    }
}
