package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.custom.WmcAbilities;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TerminusShovelItem extends ShovelItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Shovel";
    private static final List<IItemAbility> abilities = new ArrayList<>();
    private static boolean initialized = false;

    public TerminusShovelItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public List<IItemAbility> getAbilities() {
        if (!initialized) {
            initialized = true;
            initializeAbilities();
        }
        return abilities;
    }

    @Override
    public void initializeAbilities() {
        addAbility(WmcAbilities.TERRAIN_CLEANER.get());
        addAbility(WmcAbilities.GRAVITY_CHANGER.get());
        addAbility(WmcAbilities.EMPTY.get());
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
