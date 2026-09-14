package com.unk.wmc.api.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class RandomBlueprintRecipeRegistry {
    public final Map<ResourceLocation, ResultInfo> recipes = new HashMap<>();

    public record ResultInfo(int count, Item item) {};

    public void register(ResourceLocation recipeLoc, ResultInfo info) {
        recipes.put(recipeLoc, info);
    }
}
