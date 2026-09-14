package com.unk.wmc.compat.jei.subtype;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SmithingTemplateDustSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final SmithingTemplateDustSubtypeInterpreter INSTANCE = new SmithingTemplateDustSubtypeInterpreter();

    public record SubtypeKey(SimpleItemData simpleItemData, ActivatableData activatableData) {}

    @Override
    public @Nullable Object getSubtypeData(ItemStack ingredient, @NotNull UidContext context) {
        SimpleItemData simpleItemData = ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM);
        ActivatableData activatableData = ingredient.get(WmcDataComponentTypes.ACTIVATABLE);
        if (simpleItemData == null && activatableData == null) return null;
        return new SubtypeKey(simpleItemData, activatableData);
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(ItemStack ingredient, @NotNull UidContext context) {
        SimpleItemData simpleItemData = ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM);
        ActivatableData activatableData = ingredient.get(WmcDataComponentTypes.ACTIVATABLE);
        return (simpleItemData == null ? "" : simpleItemData.toString()) + "|" +
                (activatableData == null ? "" : activatableData.toString());
    }
}
