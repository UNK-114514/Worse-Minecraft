package com.unk.wmc.handler;

import com.unk.wmc.Wmc;
import com.unk.wmc.api.recipe.RandomBlueprintRecipeRegistry;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Optional;
import java.util.stream.IntStream;

@EventBusSubscriber(modid = Wmc.MOD_ID)
public class WmcRecipeHandler {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide) return;

        if (Wmc.REGISTRY.recipes.isEmpty()) return;

        Container craftMatrix = event.getInventory();
        if (!(craftMatrix instanceof CraftingContainer craftingContainer)) return;

        CraftingInput input = CraftingInput.of(
                craftingContainer.getWidth(),
                craftingContainer.getHeight(),
                IntStream.range(0, craftMatrix.getContainerSize())
                        .mapToObj(craftMatrix::getItem)
                        .toList()
        );

        RecipeManager recipeManager = level.getRecipeManager();
        Optional<RecipeHolder<CraftingRecipe>> recipeOpt =
                recipeManager.getRecipeFor(RecipeType.CRAFTING, input, level);

        if (recipeOpt.isEmpty()) return;

        ResourceLocation recipeId = recipeOpt.get().id();

        RandomBlueprintRecipeRegistry.ResultInfo info =
                Wmc.REGISTRY.recipes.get(recipeId);

        if (info == null) return;

        BlueprintData.BlueprintComponentBuilder builder =
                new BlueprintData.BlueprintComponentBuilder(info.item());

        event.getCrafting().set(
                WmcDataComponentTypes.BLUEPRINT,
                builder.getRandom(info.count())
        );
    }
}
