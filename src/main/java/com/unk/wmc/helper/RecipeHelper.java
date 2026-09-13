package com.unk.wmc.helper;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.List;

public class RecipeHelper {
    public static boolean matchAll(CraftingInput input, List<List<Item>> items, int length, int height) {
        if (items.size() != height) return false;

        for (List<Item> line : items) {
            if (line.size() != length) {
                return false;
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                if (!input.getItem(col, row).is(items.get(row).get(col))) {
                    return false;
                }
            }
        }
        return true;
    }
}
