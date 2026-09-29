package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.item.custom.ability.custom.WmcAbilities;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TerminusSwordItem extends SwordItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Sword";
    private static final List<IItemAbility> abilities = new ArrayList<>();
    private static boolean initialized = false;

    public TerminusSwordItem(Tier tier, Properties properties) {
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
        addAbility(WmcAbilities.RANGE_KILL_ALL_ENTITY.get());
        addAbility(WmcAbilities.RANGE_KILL_ALL_LIVING.get());
        addAbility(WmcAbilities.RANGE_KILL_ALL_MONSTER.get());
        addAbility(WmcAbilities.RANGE_KILL_ALL_ITEM.get());
        addAbility(WmcAbilities.RANGE_KILL_ATTACKED_ENTITY.get());
        addAbility(WmcAbilities.EMPTY.get());
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        IItemAbility selected = getSelectedAbility(stack);
        if (selected != null && selected.equals(WmcAbilities.RANGE_KILL_ATTACKED_ENTITY.get())) {
            EntityType<?> type = entity.getType();
            List<? extends Entity> entities = player.level().getEntitiesOfClass(
                    Entity.class,
                    player.getBoundingBox().inflate(128),
                    e -> e.getType() == type
            );

            SimpleAbility.applyUsedEffect(stack, player);
            player.getCooldowns().addCooldown(stack.getItem(), 10);

            EntityHelper.killAll(entities, player);
            return true;
        }

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
