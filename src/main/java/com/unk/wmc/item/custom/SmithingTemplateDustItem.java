package com.unk.wmc.item.custom;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ItemStackHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmithingTemplateDustItem extends Item {
    public SmithingTemplateDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        ActivatableData activatableData = stack.get(WmcDataComponentTypes.ACTIVATABLE);
        SimpleItemData simpleItemData = stack.get(WmcDataComponentTypes.SIMPLE_ITEM);

        Component activatedTip = Component.literal("Activated").withStyle(ChatFormatting.GREEN);
        Component unactivatedTip = Component.literal("Unactivated").withStyle(ChatFormatting.RED);

        Component unknown = Component.literal("Unknown Or Broken Data Components").withStyle(ChatFormatting.RED);

        if (activatableData == null || simpleItemData == null) return;

        if (!BuiltInRegistries.ITEM.containsKey(simpleItemData.itemId())) {
            components.add(unknown);
            return;
        }

        Item item = BuiltInRegistries.ITEM.get(simpleItemData.itemId());

        Component itemName = item.getName(item.getDefaultInstance()).copy();
        Component isActivated = activatableData.isActivated() ? activatedTip : unactivatedTip;

        components.add(itemName.copy().withStyle(ChatFormatting.GRAY));

        Component templateInfo = ItemStackHelper.getSmithingTemplateInfo(item);
        if (templateInfo != null) {
            components.add(Component.literal(" ").append(templateInfo).withStyle(ChatFormatting.BLUE));
        }

        components.add(isActivated);
    }
}
