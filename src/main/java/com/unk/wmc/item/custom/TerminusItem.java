package com.unk.wmc.item.custom;

import com.unk.wmc.helper.TerminusToolHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TerminusItem {
    default void addTooltip(@NotNull ItemStack stack, @NotNull List<Component> components) {
        TerminusToolHelper.appendMultiAbilityItemHoverText(stack, components);
        TerminusToolHelper.appendInfTooltip(components);
    }
}
