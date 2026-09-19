package com.unk.wmc.handler;

import com.unk.wmc.keybind.WmcKeyBinds;
import com.unk.wmc.network.C2SAbilitySwitchingPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = "wmc", value = Dist.CLIENT)
public class AbilityHandler {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft inst = Minecraft.getInstance();
        Player player = inst.player;

        if (player == null) return;

        while (WmcKeyBinds.FRONT_ABILITY_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new C2SAbilitySwitchingPayload(-1));
        }

        while (WmcKeyBinds.NEXT_ABILITY_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new C2SAbilitySwitchingPayload(-2));
        }

        while (WmcKeyBinds.TRIGGER_ABILITY_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new C2SAbilitySwitchingPayload(-3));
        }
    }
}