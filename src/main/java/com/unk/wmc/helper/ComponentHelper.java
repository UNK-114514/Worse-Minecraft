package com.unk.wmc.helper;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ComponentHelper {
    public static final Component UNKNOWN_DATA_COMPONENTS =
            Component.translatable("item.wmc.general.unknown_data_components").withStyle(ChatFormatting.RED);

    public static final Component UNKNOWN_ITEM =
            Component.translatable("item.wmc.general.unknown_item").withStyle(ChatFormatting.RED);

    public static final Component ACTIVATED =
            Component.translatable("item.wmc.general.activated").withStyle(ChatFormatting.GREEN);

    public static final Component UNACTIVATED =
            Component.translatable("item.wmc.general.unactivated").withStyle(ChatFormatting.RED);

    public static void addItemTooltip(
            @NotNull ItemStack stack, @NotNull Item.TooltipContext tooltipContext,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        if (stack.isEmpty()) {
            components.add(UNKNOWN_ITEM);
            return;
        }

        Component hoverText = stack.getHoverName();
        List<Component> itemHoverText = new ArrayList<>();

        if (!Screen.hasControlDown() || !hasTooltip(stack, tooltipFlag)) {
            components.add(hoverText.copy().withStyle(ChatFormatting.GRAY));
            return;
        }

        if (hasTooltip(stack, tooltipFlag)) {
            components.add(hoverText.copy().append(Component.literal(" [↓]").withStyle(ChatFormatting.GREEN)));
            stack.getItem().appendHoverText(stack, tooltipContext, itemHoverText, tooltipFlag);

            itemHoverText.forEach(component -> components.add(Component.literal(" ").append(component)));
        }
    }

    public static void addItemTooltip(
            @Nullable Item item, @NotNull Item.TooltipContext tooltipContext,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        if (item == null) {
            components.add(UNKNOWN_ITEM);
            return;
        }

        addItemTooltip(item.getDefaultInstance(), tooltipContext, components, tooltipFlag);
    }

    public static boolean hasTooltip(ItemStack stack, TooltipFlag tooltipFlag) {
        Item item = stack.getItem();
        List<Component> emptyComponents = new ArrayList<>();

        item.appendHoverText(stack, Item.TooltipContext.EMPTY, emptyComponents, tooltipFlag);

        return !emptyComponents.isEmpty();
    }
}
