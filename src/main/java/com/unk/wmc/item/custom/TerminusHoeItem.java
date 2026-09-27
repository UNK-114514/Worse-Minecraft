package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.helper.VeinMiningHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.item.custom.ability.custom.WmcAbilities;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TerminusHoeItem extends HoeItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Hoe";
    private static final List<IItemAbility> abilities = new ArrayList<>();
    private static boolean initialized = false;

    public TerminusHoeItem(Tier tier, Item.Properties properties) {
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
        addAbility(WmcAbilities.HARVEST.get());
        addAbility(WmcAbilities.GRIM_REAPER.get());
        addAbility(WmcAbilities.EMPTY.get());
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        EntityHelper.kill(entity, player);
        return true;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        IItemAbility ability = getSelectedAbility(context.getItemInHand());

        if (ability == null || !ability.equals(WmcAbilities.HARVEST)) {
            return super.useOn(context);
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (level.isClientSide) return InteractionResult.CONSUME;
        if (context.getPlayer() == null) return InteractionResult.CONSUME;

        boolean isUsed = false;
        boolean isSuccess = false;

        if (state.getBlock() instanceof FarmBlock) {
            isSuccess = VeinMiningHelper.plantSeeds(level, pos, context.getPlayer());
            isUsed = true;
        } else if (state.getBlock() instanceof CropBlock crop && crop.getAge(state) < crop.getMaxAge()) {
            isSuccess = VeinMiningHelper.boneMeal(level, pos);
            isUsed = true;
        } else if (state.getBlock() instanceof CropBlock crop && crop.getAge(state) == crop.getMaxAge()) {
            isSuccess = VeinMiningHelper.harvest(level, pos, context.getPlayer());
            isUsed = true;
        }

        if (isUsed) {
            SimpleAbility.applyUsedEffect(context.getItemInHand(), context.getPlayer());
            context.getPlayer().getCooldowns().addCooldown(context.getItemInHand().getItem(), 100);
        }

        return isSuccess ? InteractionResult.SUCCESS : InteractionResult.PASS;
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
