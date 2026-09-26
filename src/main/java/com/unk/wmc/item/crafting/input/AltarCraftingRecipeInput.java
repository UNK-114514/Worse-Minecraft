package com.unk.wmc.item.crafting.input;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record AltarCraftingRecipeInput(
        ItemStack n,    // ↑
        ItemStack en,   // ↗
        ItemStack e,    // →
        ItemStack es,   // ↘
        ItemStack s,    // ↓
        ItemStack ws,   // ↙
        ItemStack w,    // ←
        ItemStack wn,   // ↖
        ItemStack mid   // o
) implements RecipeInput {
    public static final AltarCraftingRecipeInput EMPTY = new AltarCraftingRecipeInput(
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY
    );

    @Override
    public @NotNull ItemStack getItem(int i) {
        return switch (i) {
            case 0 -> n;
            case 1 -> en;
            case 2 -> e;
            case 3 -> es;
            case 4 -> s;
            case 5 -> ws;
            case 6 -> w;
            case 7 -> wn;
            case 8 -> mid;
            default -> throw new IllegalStateException("Invalid item stack index: " + i);
        };
    }

    @Override
    public int size() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        return n.isEmpty()
                && en.isEmpty()
                && e.isEmpty()
                && es.isEmpty()
                && s.isEmpty()
                && ws.isEmpty()
                && w.isEmpty()
                && wn.isEmpty()
                && mid.isEmpty();
    }

    public AltarCraftingRecipeInput rotate(int times) {
        return new AltarCraftingRecipeInput(
                getItem((times * 2) % 8),
                getItem((1 + times * 2) % 8),
                getItem((2 + times * 2) % 8),
                getItem((3 + times * 2) % 8),
                getItem((4 + times * 2) % 8),
                getItem((5 + times * 2) % 8),
                getItem((6 + times * 2) % 8),
                getItem((7 + times * 2) % 8),
                mid
        );
    }
}
