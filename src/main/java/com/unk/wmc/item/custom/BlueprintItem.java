package com.unk.wmc.item.custom;

import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ComponentHelper;
import com.unk.wmc.helper.ItemStackHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

        ResourceLocation nextItemLoc = blueprintData.remainingSteps().getFirst();

        Item nextItem = ItemStackHelper.fromResourceLocation(nextItemLoc);
        Item resultItem = ItemStackHelper.fromResourceLocation(blueprintData.itemId());

        Component progressInfo = Component.translatable("item.wmc.blueprint.description.progress")
                .append(
                        blueprintData.completedSteps().size()
                                + "/"
                                + (blueprintData.remainingSteps().size() + blueprintData.completedSteps().size())
                ).withStyle(ChatFormatting.GREEN);

//        Component progressInfo =
//                Component.translatable(
//                        Util.makeDescriptionId(
//                                "item",
//                                ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.progress")
//                        )
//                ).append(
//                        blueprintData.completedSteps().size()
//                        + "/"
//                        + (blueprintData.remainingSteps().size() + blueprintData.completedSteps().size())
//                ).withStyle(ChatFormatting.GREEN);

        components.add(Component.translatable("item.wmc.blueprint.description.result").withStyle(ChatFormatting.BLUE));

//        components.add(
//                Component.translatable(
//                        Util.makeDescriptionId(
//                                "item",
//                                ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.result")
//                        )
//                ).withStyle(ChatFormatting.BLUE));

        ComponentHelper.addItemTooltip(resultItem, tooltipContext, components, tooltipFlag);

        components.add(CommonComponents.EMPTY);

        components.add(Component.translatable("item.wmc.blueprint.description.next_step").withStyle(ChatFormatting.BLUE));

//        components.add(Component.translatable(
//                Util.makeDescriptionId(
//                        "item",
//                        ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.next_step")
//                )
//        ).withStyle(ChatFormatting.BLUE));

        ComponentHelper.addItemTooltip(nextItem, tooltipContext, components, tooltipFlag);
        components.add(progressInfo);
    }

    private void addRemainingStepsTooltip(
            @NotNull TooltipContext tooltipContext, @NotNull List<Component> components,
            @NotNull TooltipFlag tooltipFlag, @NotNull BlueprintData blueprintData) {
        if (blueprintData.remainingSteps().isEmpty()) {
            components.add(ComponentHelper.UNKNOWN_DATA_COMPONENTS);
            return;
        }

        for (ResourceLocation loc : blueprintData.remainingSteps()) {
            Item nextItem = ItemStackHelper.fromResourceLocation(loc);
            ComponentHelper.addItemTooltip(nextItem, tooltipContext, components, tooltipFlag);
        }
    }
}
