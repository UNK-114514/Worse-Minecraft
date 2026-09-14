package com.unk.wmc.compat.jei.subtype;

import com.unk.wmc.component.SimpleItemStackData;
import com.unk.wmc.component.WmcDataComponentTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleItemSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final SimpleItemSubtypeInterpreter INSTANCE = new SimpleItemSubtypeInterpreter();

    @Override
    public @Nullable Object getSubtypeData(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        return ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM_STACK);
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        SimpleItemStackData data = ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM_STACK);
        return data == null ? "" : data.stack().toString();
    }
}
