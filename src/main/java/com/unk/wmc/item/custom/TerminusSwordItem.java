package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.MarkingAbilityBuilder;
import com.unk.wmc.item.custom.ability.RangeKillAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.item.custom.ability.SimpleMultiAbilityItem;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TerminusSwordItem extends SimpleMultiAbilityItem {
    private static final String NAME = "Terminus Sword";

    public TerminusSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeAbilities() {
        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        10,
                        Component.literal("Range Kill (All Entity)"),
                        Component.literal("Kill every entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        Entity.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        10,
                        Component.literal("Range Kill (All Living)"),
                        Component.literal("Kill every living entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        LivingEntity.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        10,
                        Component.literal("Range Kill (All Monster)"),
                        Component.literal("Kill every monster in 128 blocks").withStyle(ChatFormatting.GRAY),
                        Monster.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        -1,
                        Component.literal("Range Kill (All Item)"),
                        Component.literal("Kill every item entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        ItemEntity.class
                ).build()
        );

        addAbility(
                MarkingAbilityBuilder.of(
                        Component.literal("Range Kill (Attacked Entity)"),
                        Component.literal("Kill all the entity of the same type as the ones you attacked")
                ).build()
        );

        addAbility(SimpleAbility.EMPTY);
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (getSelectedIndex(stack) == 4) {
            EntityType<?> type = entity.getType();
            List<? extends Entity> entities = player.level().getEntitiesOfClass(
                    Entity.class,
                    player.getBoundingBox().inflate(128),
                    e -> e.getType() == type
            );

            EntityHelper.killAll(entities, player);
            return true;
        }

        EntityHelper.kill(entity, player);
        return true;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return RainbowComponent.of(NAME, 0.75F, 1F, 0.01F);
    }
}
