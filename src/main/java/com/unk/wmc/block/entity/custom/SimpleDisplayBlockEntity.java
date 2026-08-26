package com.unk.wmc.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class SimpleDisplayBlockEntity extends BlockEntity {
    public final float baseModelHeight;

    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public SimpleDisplayBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, float baseModelHeight) {
        super(type, pos, blockState);
        this.baseModelHeight = baseModelHeight;
    }

//    public static void tick(Level level, BlockPos pos, BlockState blockState, SimpleDisplayBlockEntity be) {
//
//    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(compoundTag, provider);

        this.inventory.deserializeNBT(provider, compoundTag.getCompound("inventory"));

//        this.animationTick = compoundTag.getInt("animation_tick");
//        this.shouldPlayAnimation = compoundTag.getBoolean("should_play_animation");
//        this.isPlayingAnimation = compoundTag.getBoolean("is_playing_animation");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.put("inventory", this.inventory.serializeNBT(provider));

//        compoundTag.putInt("animation_tick", this.animationTick);
//        compoundTag.putBoolean("should_play_animation", this.shouldPlayAnimation);
//        compoundTag.putBoolean("is_playing_animation", this.isPlayingAnimation);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
