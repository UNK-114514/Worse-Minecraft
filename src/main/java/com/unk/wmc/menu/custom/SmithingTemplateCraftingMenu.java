package com.unk.wmc.menu.custom;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.custom.SmithingTemplateCraftingRecipe;
import com.unk.wmc.item.crafting.input.SmithingTemplateCraftingRecipeInput;
import com.unk.wmc.menu.WmcMenuTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.OptionalInt;

public class SmithingTemplateCraftingMenu extends ItemCombinerMenu {
    public RecipeHolder<SmithingTemplateCraftingRecipe> selectedRecipe;

    private final Level level;
    private final List<RecipeHolder<SmithingTemplateCraftingRecipe>> recipes;

    public SmithingTemplateCraftingMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public SmithingTemplateCraftingMenu(int id, Inventory inventory, ContainerLevelAccess levelAccess) {
        super(WmcMenuTypes.SMITHING_TEMPLATE_CRAFTING_MENU_TYPE.get(), id, inventory, levelAccess);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager()
                .getAllRecipesFor(WmcRecipeTypes.SMITHING_TEMPLATE_CRAFTING_RECIPE_TYPE.get());
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean b) {
        return this.selectedRecipe != null && (this.selectedRecipe.value()).matches(this.createRecipeInput(), this.level);
    }

    @Override
    protected void onTake(@NotNull Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
    }

    @Override
    protected boolean isValidBlock(BlockState blockState) {
        return blockState.is(WmcBlocks.SMITHING_TEMPLATE_CRAFTING_TABLE.get());
    }

    @Override
    public void createResult() {
        SmithingTemplateCraftingRecipeInput input = this.createRecipeInput();
        List<RecipeHolder<SmithingTemplateCraftingRecipe>> list = this.level.getRecipeManager().getRecipesFor(WmcRecipeTypes.SMITHING_TEMPLATE_CRAFTING_RECIPE_TYPE.get(), input, this.level);

        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            this.selectedRecipe = null;
        } else {
            RecipeHolder<SmithingTemplateCraftingRecipe> recipeholder = list.getFirst();
            ItemStack itemstack = recipeholder.value().assemble(input, this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = recipeholder;
                this.resultSlots.setRecipeUsed(recipeholder);
                this.resultSlots.setItem(0, itemstack);
            }
        }
    }

    @Override
    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 26, 48,
                        (itemStack) -> this.recipes.stream().anyMatch((recipe) -> (recipe.value()).isBaseTemplate(itemStack)))
                .withSlot(1, 44, 48,
                        (itemStack) -> this.recipes.stream().anyMatch((recipe) -> (recipe.value()).isTemplateCore(itemStack)))
                .withResultSlot(2, 98, 48)
                .build();
    }

    private SmithingTemplateCraftingRecipeInput createRecipeInput() {
        return new SmithingTemplateCraftingRecipeInput(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemstack = this.inputSlots.getItem(index);
        if (!itemstack.isEmpty()) {
            itemstack.shrink(1);
            this.inputSlots.setItem(index, itemstack);
        }
    }

    public int getSlotToQuickMoveTo(@NotNull ItemStack itemStack) {
        return this.findSlotToQuickMoveTo(itemStack).orElse(0);
    }

    private static OptionalInt findSlotMatchingIngredient(SmithingTemplateCraftingRecipe recipe, ItemStack itemStack) {
        if (recipe.isBaseTemplate(itemStack)) {
            return OptionalInt.of(0);
        } else if (recipe.isTemplateCore(itemStack)) {
            return OptionalInt.of(1);
        }
        return OptionalInt.empty();
    }

    public boolean canTakeItemForPickAll(@NotNull ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    public boolean canMoveIntoInputSlots(@NotNull ItemStack stack) {
        return this.findSlotToQuickMoveTo(stack).isPresent();
    }

    private OptionalInt findSlotToQuickMoveTo(ItemStack itemStack) {
        return this.recipes.stream().flatMapToInt((recipe) ->
                findSlotMatchingIngredient(recipe.value(), itemStack).stream()).filter((slotId) -> !this.getSlot(slotId).hasItem()).findFirst();
    }
}
