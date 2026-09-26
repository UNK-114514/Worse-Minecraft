package com.unk.wmc.helper;

import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class SoundHelper {
    public static void playSoundToPlayer(ServerPlayer player, SoundEvent event) {
        player.connection.send(
                new ClientboundSoundPacket(Holder.direct(event),
                        SoundSource.PLAYERS,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        1.0F,
                        1.0F,
                        player.level().getRandom().nextLong()
                )
        );
    }
}
