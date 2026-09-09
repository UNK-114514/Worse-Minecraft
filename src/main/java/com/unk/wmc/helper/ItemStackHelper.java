package com.unk.wmc.helper;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    public static Item fromResourceLocation(ResourceLocation loc) {
        if (BuiltInRegistries.ITEM.containsKey(loc)) {
            return BuiltInRegistries.ITEM.get(loc);
        }
        return Items.AIR;
    }

    public static ItemStack getDustOf(Item item, boolean isActivated) {
        ItemStack result = new ItemStack(WmcItems.SMITHING_TEMPLATE_DUST.get());
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);

        result.set(WmcDataComponentTypes.SIMPLE_ITEM.get(), new SimpleItemData(location));
        result.set(WmcDataComponentTypes.ACTIVATABLE.get(), new ActivatableData(isActivated));

        return result;
    }

    public static ItemStack withActivatable(ItemStack stack, boolean isActivated) {
        ItemStack result = stack.copy();
        result.set(WmcDataComponentTypes.ACTIVATABLE.get(), new ActivatableData(isActivated));

        return result;
    }

    public static ItemStack doNextStep(ItemStack stack) {
        ItemStack copy = stack.copy();

        BlueprintData data = copy.get(WmcDataComponentTypes.BLUEPRINT);

        if (data == null) return copy;
        if (!BuiltInRegistries.ITEM.containsKey(data.itemId())) return stack;
        if (data.remainingSteps().size() <= 1) {
            ResourceLocation resultLoc = data.itemId();
            return fromResourceLocation(resultLoc).getDefaultInstance();
        }

        List<ResourceLocation> remaining = new LinkedList<>(data.remainingSteps());
        ResourceLocation step = remaining.removeFirst();

        List<ResourceLocation> completed = new LinkedList<>(data.completedSteps());
        completed.add(step);

        BlueprintData newData = new BlueprintData(data.itemId(), remaining, completed);
        copy.set(WmcDataComponentTypes.BLUEPRINT.get(), newData);

        return copy;
    }

    public static @Nullable Component getSmithingTemplateInfo(Item item) {
        if (item instanceof SmithingTemplateItem template) {
            return template.upgradeDescription.copy().withStyle(ChatFormatting.RESET);
        }
        return null;
    }
}
