package com.unk.wmc.helper;

import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TerminusToolHelper {
    public static void appendMultiAbilityItemHoverText(@NotNull ItemStack stack, @NotNull List<Component> components) {
        if (!(stack.getItem() instanceof IMultiAbilityItem multi)) return;

        components.add(Component.literal("Selected Ability: ").append(multi.getSelectedName(stack)));
        Component description = multi.getSelectedDescription(stack);
        if (Screen.hasShiftDown() && description != null) {
            components.add(multi.getSelectedDescription(stack));
        }
    }

    public static void appendInfTooltip(@NotNull List<Component> components) {
        components.add(CommonComponents.EMPTY);
        components.add(Component.translatable("item.modifiers.mainhand").withStyle(ChatFormatting.GRAY));
        components.add(RainbowComponent.of(" Infinity ", 0.75F, 1F, 0.05F, 0).copy().append(Component.translatable("attribute.name.generic.attack_damage").withStyle(ChatFormatting.DARK_GREEN)));
        components.add(RainbowComponent.of(" Infinity ", 0.75F, 1F, 0.05F, 0).copy().append(Component.translatable("attribute.name.generic.attack_speed").withStyle(ChatFormatting.DARK_GREEN)));
    }
}
