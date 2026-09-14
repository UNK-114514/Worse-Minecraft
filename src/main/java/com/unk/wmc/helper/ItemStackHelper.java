package com.unk.wmc.helper;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.SimpleItemStackData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class ItemStackHelper {
    public static void givePlayerStack(Player player, ItemStack stack) {
        boolean flag = player.addItem(stack);

        if (!flag && !stack.isEmpty()) {
            player.drop(stack, false);
        }
    }

    public static ItemStack mergeStackForcibly(ItemStack stackA, ItemStack stackB) {
        return setStackCount(stackA, stackA.getCount() + stackB.getCount());
    }

    public static ItemStack setStackCount(ItemStack stack, int count) {
        ItemStack result = stack.copy();
        result.setCount(count);

        return result;
    }

    public static @Nullable Item fromResourceLocation(ResourceLocation loc) {
        if (BuiltInRegistries.ITEM.containsKey(loc)) {
            return BuiltInRegistries.ITEM.get(loc);
        }
        return null;
    }

    public static ItemStack getDustOf(Item item, boolean isActivated) {
        ItemStack result = new ItemStack(WmcItems.SMITHING_TEMPLATE_DUST.get());
        result.set(WmcDataComponentTypes.SIMPLE_ITEM_STACK.get(), new SimpleItemStackData(item.getDefaultInstance()));
        result.set(WmcDataComponentTypes.ACTIVATABLE.get(), new ActivatableData(isActivated));

        return result;
    }

    public static ItemStack withActivatable(ItemStack stack, boolean isActivated) {
        ItemStack result = stack.copy();
        result.set(WmcDataComponentTypes.ACTIVATABLE.get(), new ActivatableData(isActivated));

        return result;
    }

    public static ItemStack withActivatable(Item item, boolean isActivated) {
        ItemStack stack = item.getDefaultInstance();
        return withActivatable(stack, isActivated);
    }

    public static ItemStack doNextStep(ItemStack stack) {
        ItemStack copy = stack.copy();

        BlueprintData data = copy.get(WmcDataComponentTypes.BLUEPRINT);

        if (data == null) return copy;
        if (data.remainingSteps().size() <= 1) {
            Item result = data.stack().getItem();
            return result.getDefaultInstance();
        }

        List<ItemStack> remaining = new LinkedList<>(data.remainingSteps());
        ItemStack step = remaining.removeFirst();

        List<ItemStack> completed = new LinkedList<>(data.completedSteps());
        completed.add(step);

        BlueprintData newData = new BlueprintData(data.stack(), remaining, completed);
        copy.set(WmcDataComponentTypes.BLUEPRINT.get(), newData);

        return copy;
    }

    public static @Nullable Component getSmithingTemplateInfo(Item item) {
        if (item instanceof SmithingTemplateItem template) {
            return template.upgradeDescription.copy().withStyle(ChatFormatting.RESET);
        }
        return null;
    }

    public static ItemStack getRandomBlueprint(int count) {
        ItemStack result = WmcItems.BLUEPRINT.get().getDefaultInstance();
        BlueprintData.BlueprintComponentBuilder builder = new BlueprintData.BlueprintComponentBuilder(WmcItems.RANDOM_BLUEPRINT.get());

        result.set(WmcDataComponentTypes.BLUEPRINT, builder.getRandom(count));

        return result;
    }
}
