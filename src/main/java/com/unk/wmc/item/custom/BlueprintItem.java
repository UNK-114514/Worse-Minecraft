package com.unk.wmc.item.custom;

import com.unk.wmc.Wmc;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ComponentHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlueprintItem extends Item {
    public BlueprintItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack, @NotNull TooltipContext tooltipContext,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        BlueprintData blueprintData = stack.get(WmcDataComponentTypes.BLUEPRINT);

        Wmc.LOGGER.info("[Blueprint] side={} stack={} count={} data={}",
                stack.isEmpty() ? "client" : "unknown",
                stack.getItem(),
                stack.getCount(),
                blueprintData);

        if (blueprintData == null) return;

        if (Screen.hasShiftDown()) {
            addRemainingStepsTooltip(tooltipContext, components, tooltipFlag, blueprintData);
        } else {
            addProgressTooltip(tooltipContext, components, tooltipFlag, blueprintData);
        }
    }

    private void addProgressTooltip(
            @NotNull TooltipContext tooltipContext, @NotNull List<Component> components,
            @NotNull TooltipFlag tooltipFlag, @NotNull BlueprintData blueprintData) {
        if (blueprintData.remainingSteps().isEmpty()) {
            components.add(ComponentHelper.UNKNOWN_DATA_COMPONENTS);
            return;
        }

        ItemStack nextStack = blueprintData.remainingSteps().getFirst();
        ItemStack resultStack = blueprintData.result();

        Component progressInfo = Component.translatable("item.wmc.blueprint.description.progress")
                .append(
                        blueprintData.completedSteps().size()
                                + "/"
                                + (blueprintData.remainingSteps().size() + blueprintData.completedSteps().size())
                ).withStyle(ChatFormatting.GREEN);

        components.add(Component.translatable("item.wmc.blueprint.description.result").withStyle(ChatFormatting.BLUE));

        ComponentHelper.addTooltip(resultStack, tooltipContext, components, tooltipFlag);

        components.add(CommonComponents.EMPTY);

        components.add(Component.translatable("item.wmc.blueprint.description.next_step").withStyle(ChatFormatting.BLUE));

        ComponentHelper.addTooltip(nextStack, tooltipContext, components, tooltipFlag);
        components.add(progressInfo);
    }

    private void addRemainingStepsTooltip(
            @NotNull TooltipContext tooltipContext, @NotNull List<Component> components,
            @NotNull TooltipFlag tooltipFlag, @NotNull BlueprintData blueprintData) {
        if (blueprintData.remainingSteps().isEmpty()) {
            components.add(ComponentHelper.UNKNOWN_DATA_COMPONENTS);
            return;
        }

        for (ItemStack step : blueprintData.remainingSteps()) {
            ComponentHelper.addTooltip(step, tooltipContext, components, tooltipFlag);
        }
    }
}
