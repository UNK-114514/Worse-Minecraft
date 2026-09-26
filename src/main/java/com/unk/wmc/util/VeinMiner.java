package com.unk.wmc.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Predicate;

public class VeinMiner {
    public static List<BlockPos> veinMineBFS(Level level, BlockPos start, int max, Predicate<BlockPos> shouldMine) {
        return veinMineBFS(
                level,
                start,
                max,
                shouldMine,
                (pos) -> true
        );
    }

    public static List<BlockPos> veinMineBFS(
            Level level, BlockPos start, int max,
            Predicate<BlockPos> shouldMine, Predicate<BlockPos> shouldCount) {
        Queue<BlockPos> nodes = new ArrayDeque<>();
        LinkedHashSet<BlockPos> visited = new LinkedHashSet<>();

        int count = 0;

        Collection<Direction> shuffled = Direction.allShuffled(level.getRandom());

        nodes.add(start);
        visited.add(start);
        if (shouldCount.test(start)) {
            count += 1;
        }

        while (!nodes.isEmpty() && count < max && visited.size() < 16384) {
            BlockPos node = nodes.poll();

            for (Direction direction : shuffled) {
                if (count >= max) break;

                BlockPos next = node.relative(direction);
                if (visited.contains(next)) continue;
                if (!shouldMine.test(next)) continue;

                visited.add(next);
                nodes.add(next);
                if (shouldCount.test(next)) {
                    count += 1;
                }
            }
        }

        return visited.stream().toList();
    }

    public static List<BlockPos> veinMineArea(
            BlockPos start, Predicate<BlockPos> shouldMine,
            int maxXOffset, int maxYOffset, int maxZOffset, int maxCount) {
        List<BlockPos> result = new ArrayList<>();

        BlockPos startPos = start.offset(-maxXOffset, -maxYOffset, -maxZOffset);
        BlockPos endPos = start.offset(maxXOffset, maxYOffset, maxZOffset);

        for (BlockPos pos : BlockPos.betweenClosed(startPos, endPos)) {
            if (maxCount != -1 && result.size() >= maxCount) return result;
            if (shouldMine.test(pos)) {
                result.add(pos.immutable());
            }
        }

        return result;
    }

    public static void breakAndSpawnResource(Level level, BlockPos pos, LivingEntity miner, int multiplier) {
        spawnResource(level, level.getBlockState(pos), miner.blockPosition().atY((int) Math.round(miner.getY())), miner, multiplier);
        level.destroyBlock(pos, false, miner);
    }

    public static void spawnResource(Level level, BlockState state, BlockPos pos, LivingEntity miner, int multiplier) {
        if (state.isAir()) return;
        if (!level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) return;
        if (multiplier == 0) return;

        BlockEntity be = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        for (int count = 0; count < multiplier; count++) {
            Block.dropResources(state, level, pos, be, miner, ItemStack.EMPTY);
        }
    }

    public static void mineAll(List<BlockPos> targets, Level level, int step, LivingEntity miner, int multiplier) {
        if (targets == null || targets.isEmpty()) return;
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (step <= 0) step = 1;

        mineBatch(targets, serverLevel, miner, 0, step, multiplier);
    }

    public static void mineBatch(
            List<BlockPos> targets, ServerLevel level, LivingEntity miner,
            int start, int step, int multiplier) {
        int end = Math.min(start + step, targets.size());

        for (int i = start; i < end; i++) {
            BlockPos pos = targets.get(i);

            if (!level.getBlockState(pos).isAir()) {
                breakAndSpawnResource(level, pos, miner, multiplier);
                level.destroyBlock(pos, false, miner);
            }
        }

        if (end < targets.size()) {
            DelayedTickTask.schedule(level, 1, () -> mineBatch(targets, level, miner, end, step, multiplier));
        }
    }
}
