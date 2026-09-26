package com.unk.wmc.helper;

import com.unk.wmc.util.VeinMiner;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class VeinMiningHelper {
    public static boolean plantSeeds(Level level, BlockPos start, Player player) {
        ItemStack seed = player.getOffhandItem();
        if (!(seed.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CropBlock crop)) return false;
        List<BlockPos> targets = VeinMiner.veinMineArea(
                start,
                target -> level.getBlockState(target).getBlock() instanceof FarmBlock
                        && level.getBlockState(target.above()).isAir(),
                9, 3, 9, -1
        );

        targets.forEach(target -> level.setBlock(target.above(), crop.getStateForAge(0), 3));
        return true;
    }

    public static boolean boneMeal(Level level, BlockPos start) {
        BlockState state = level.getBlockState(start);
        if (!(state.getBlock() instanceof CropBlock crop)) return false;

        List<BlockPos> targets = VeinMiner.veinMineArea(
                start,
                target -> level.getBlockState(target).getBlock() instanceof CropBlock targetCrop
                        && targetCrop.getAge(state) < crop.getMaxAge(),
                9, 3, 9, -1
        );

        targets.forEach(target -> {
            level.setBlock(target, crop.getStateForAge(crop.getMaxAge()), 3);
            level.levelEvent(1505, target, 15);
        });

        return true;
    }

    public static boolean harvest(Level level, BlockPos start, Player player) {
        List<BlockPos> targets = VeinMiner.veinMineArea(
                start,
                target -> level.getBlockState(target).getBlock() instanceof CropBlock,
                9, 3, 9, -1
        );

        targets.forEach(target -> VeinMiner.breakAndSpawnResource(level, target, player, 5));
        return true;
    }
}
