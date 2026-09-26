package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.MarkingAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TerminusPickaxeItem extends PickaxeItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Pickaxe";
    private static final List<IItemAbility> abilities = new ArrayList<>();

    public TerminusPickaxeItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
        initializeAbilities();
    }

    @Override
    public List<IItemAbility> getAbilities() {
        return abilities;
    }

    @Override
    public void initializeAbilities() {
        addAbility(
                MarkingAbilityBuilder.of(
                        Component.literal("Vein Miner"),
                        Component.literal("Max: 1024").withStyle(ChatFormatting.GRAY)
                ).build()
        );

        addAbility(
                MarkingAbilityBuilder.of(
                        Component.literal("Super Silk Touch"),
                        Component.literal("").withStyle(ChatFormatting.GRAY)
                ).build()
        );

        addAbility(SimpleAbility.EMPTY);
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        EntityHelper.kill(entity, player);
        return true;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return RainbowComponent.of(NAME);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack, @NotNull Item.TooltipContext context,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        addTooltip(stack, components);
    }
}
