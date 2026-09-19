package com.unk.wmc.network;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.MultiAbilityItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record C2SAbilitySwitchingPayload(int action) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SAbilitySwitchingPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "ability_key_pressed"));

    public static final StreamCodec<RegistryFriendlyByteBuf, C2SAbilitySwitchingPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, C2SAbilitySwitchingPayload::action,
                    C2SAbilitySwitchingPayload::new
            );


    public static void handle(C2SAbilitySwitchingPayload payload, IPayloadContext context) {
        ItemStack stack = context.player().getMainHandItem();
        if (!(stack.getItem() instanceof MultiAbilityItem multi)) return;

        if (payload.action() >= 0) {
            multi.switchAbility(stack, payload.action(), context.player());
        } else if (payload.action() == -1) {
            multi.selectNext(stack, -1, context.player());
        } else if (payload.action() == -2) {
            multi.selectNext(stack, 1, context.player());
        } else if (payload.action() == -3) {
            IItemAbility ability = multi.getSelectedAbility(stack);

            if (ability == null) return;
            if (!ability.canTriggerByHotKey()) return;

            ability.trigger(stack, context.player());
        }
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
