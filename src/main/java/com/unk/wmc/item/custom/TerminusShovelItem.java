package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.MarkingAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.util.RainbowComponent;
import com.unk.wmc.util.VeinMiner;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TerminusShovelItem extends ShovelItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Shovel";
    private static final List<IItemAbility> abilities = new ArrayList<>();

    public TerminusShovelItem(Tier tier, Properties properties) {
        super(tier, properties);
        initializeAbilities();
    }

    @Override
    public List<IItemAbility> getAbilities() {
        return abilities;
    }

    @Override
    public void initializeAbilities() {
        addAbility(new SimpleAbility() {
            @Override
            public void trigger(ItemStack stack, Player player) {
                List<BlockPos> targets = VeinMiner.veinMineBFS(
                        player.level(),
                        player.blockPosition(),
                        16384,
                        (target) -> target.getY() >= player.blockPosition().getY()
                );
                VeinMiner.mineAll(targets, player.level(), 128, player, 0);
            }

            @Override
            public boolean canTriggerByHotKey() {
                return true;
            }

            @Override
            public @Nullable Component getAbilityDescription() {
                return Component.literal("Destroy blocks that higher than you");
            }

            @Override
            public @NotNull Component getAbilityName() {
                return Component.literal("Terrain Cleaner");
            }
        });

        addAbility(
                new MarkingAbilityBuilder(
                        Component.literal("Gravity Changer"),
                        Component.literal("Change Entity's Gravity")
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
