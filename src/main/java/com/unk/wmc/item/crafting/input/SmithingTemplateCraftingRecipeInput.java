package com.unk.wmc.item.crafting.input;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record SmithingTemplateCraftingRecipeInput(ItemStack baseTemplate, ItemStack templateCore) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int i) {
        return switch (i) {
            case 0 -> baseTemplate;
            case 1 -> templateCore;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + i);
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
