package com.unk.wmc.helper;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

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
}
