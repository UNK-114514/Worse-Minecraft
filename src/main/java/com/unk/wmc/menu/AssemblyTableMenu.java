package com.unk.wmc.menu;

import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.WmcItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AssemblyTableMenu extends ItemCombinerMenu {
    public AssemblyTableMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public AssemblyTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(WmcMenuTypes.BLUEPRINT_DRAFTER_MENU.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean b) {
        return true;
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack itemStack) {
        shrinkStackInSlot(0);
        shrinkStackInSlot(1);
    }

    @Override
    protected boolean isValidBlock(@NotNull BlockState blockState) {
        return blockState.is(WmcBlocks.BLUEPRINT_DRAFTER);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return super.stillValid(player);
    }

    @Override
    public void createResult() {
        ItemStack slot0 = inputSlots.getItem(0);
        ItemStack slot1 = inputSlots.getItem(1);
        BlueprintData blueprintData = slot1.get(WmcDataComponentTypes.BLUEPRINT);

        this.resultSlots.setItem(2, ItemStack.EMPTY);

        if (blueprintData == null) return;
        if (blueprintData.remainingSteps().isEmpty()) return;

        ResourceLocation location = blueprintData.remainingSteps().getFirst();

        if (!BuiltInRegistries.ITEM.containsKey(location)) return;

        Item requiredItem = BuiltInRegistries.ITEM.get(location);

        if (requiredItem == slot0.getItem()) {
            this.resultSlots.setItem(2, ItemStackHelper.doNextStep(slot1));
        }
    }

    @Override
    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 32, (stack) -> true)
                .withSlot(1, 76, 32, AssemblyTableMenu::isBlueprintOrPaper)
                .withResultSlot(2, 134, 32)
                .build();
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (0 <= index && index < 3) {
                if (!moveItemStackTo(stack, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (3 <= index && index < 40) {
                if (stack.getItem() == Items.PAPER || stack.getItem() == WmcItems.BLUEPRINT.get()) {
                    if (!moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return result;
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemstack = this.inputSlots.getItem(index);
        if (!itemstack.isEmpty()) {
            itemstack.shrink(1);
            this.inputSlots.setItem(index, itemstack);
        }
    }

    private static boolean isBlueprintOrPaper(ItemStack stack) {
        return stack.getItem() == WmcItems.BLUEPRINT.get() || stack.getItem() == Items.PAPER;
    }
}