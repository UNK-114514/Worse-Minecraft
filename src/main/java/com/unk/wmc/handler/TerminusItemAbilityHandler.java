package com.unk.wmc.handler;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.custom.TerminusAxeItem;
import com.unk.wmc.item.custom.TerminusPickaxeItem;
import com.unk.wmc.item.custom.TerminusShovelItem;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.item.custom.ability.custom.WmcAbilities;
import com.unk.wmc.util.VeinMiner;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

@EventBusSubscriber(modid = Wmc.MOD_ID)
public class TerminusItemAbilityHandler {
    @SubscribeEvent
    public static void onAxeBreakingBlock(BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getMainHandItem();
        Level level = event.getPlayer().level();
        BlockPos pos = event.getPos();

        if (event.getPlayer().getCooldowns().isOnCooldown(stack.getItem())) return;

        if (!(stack.getItem() instanceof TerminusAxeItem axe)) return;

        IItemAbility ability = axe.getSelectedAbility(stack);

        if (ability == null) return;
        if (!ability.equals(WmcAbilities.TREE_BREAKER.get())) return;
        if (!level.getBlockState(pos).is(BlockTags.LOGS)) return;

        List<BlockPos> posList = VeinMiner.veinMineBFS(
                level,
                pos,
                1024,
                (targetPos) -> level.getBlockState(targetPos).is(BlockTags.LOGS) ||
                        level.getBlockState(targetPos).is(BlockTags.LEAVES),
                (target) -> level.getBlockState(target).is(BlockTags.LOGS)
        );

        VeinMiner.mineAll(posList, level, 64, event.getPlayer(), 5);

        event.getPlayer().getCooldowns().addCooldown(stack.getItem(), 200);
        SimpleAbility.applyUsedEffect(stack, event.getPlayer());
    }

    @SubscribeEvent
    public static void onPickaxeBreakingBlock(BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getMainHandItem();
        Level level = event.getPlayer().level();
        BlockPos pos = event.getPos();

        if (event.getPlayer().getCooldowns().isOnCooldown(stack.getItem())) return;

        if (!(stack.getItem() instanceof TerminusPickaxeItem pickaxe)) return;
        IItemAbility ability = pickaxe.getSelectedAbility(stack);

        if (ability == null) return;
        if (!ability.equals(WmcAbilities.VEIN_MINER.get())) return;

        List<BlockPos> posList = VeinMiner.veinMineBFS(
                level,
                pos,
                1024,
                (targetPos) -> level.getBlockState(targetPos).is(event.getState().getBlock())
        );

        VeinMiner.mineAll(posList, level, 64, event.getPlayer(), 1);

        event.getPlayer().getCooldowns().addCooldown(stack.getItem(), 40);
        SimpleAbility.applyUsedEffect(stack, event.getPlayer());
    }

    @SubscribeEvent
    public static void onShovelUseOnEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();

        if (player.getCooldowns().isOnCooldown(event.getItemStack().getItem())) return;

        Entity interacted = event.getTarget();

        if (!(player.getMainHandItem().getItem() instanceof TerminusShovelItem shovel)) return;
        if (shovel.getSelectedIndex(player.getMainHandItem()) != 1) return;

        interacted.setDeltaMovement(0, 5, 0);

        player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 20);
        SimpleAbility.applyUsedEffect(player.getMainHandItem(), player);
    }

    @SubscribeEvent
    public static void onPickaxeLeftClickingBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getEntity().getMainHandItem();

        if (event.getEntity().getCooldowns().isOnCooldown(stack.getItem())) return;

        Level level = event.getEntity().level();

        if (level.isClientSide) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!(stack.getItem() instanceof TerminusPickaxeItem pickaxe)) return;

        IItemAbility ability = pickaxe.getSelectedAbility(stack);

        if (ability == null) return;
        if (ability.equals(WmcAbilities.VEIN_MINER.get())) return;

        if (ability.equals(WmcAbilities.SUPER_SILK_TOUCH.get())) {
            Block.popResource(level, pos, state.getBlock().asItem().getDefaultInstance());
            level.destroyBlock(pos, false);
            return;
        }
        level.destroyBlock(pos, true, event.getEntity());
    }

    @SubscribeEvent
    public static void onCheckingTool(PlayerEvent.HarvestCheck event) {
        if (event.getEntity().getMainHandItem().is(WmcItems.TERMINUS_PICKAXE)) {
            event.setCanHarvest(true);
        }
    }

    @SubscribeEvent
    public static void onGettingMiningSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().getMainHandItem().is(WmcItems.TERMINUS_PICKAXE)) {
            event.setNewSpeed(Float.POSITIVE_INFINITY);
        }
    }
}
