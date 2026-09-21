package com.unk.wmc.helper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class EntityHelper {
    public static void kill(Entity entity, Player attacker) {
        entity.hurt(attacker.damageSources().playerAttack(attacker), Float.MAX_VALUE);

        if (!entity.isAlive()) return;

        if (!(entity instanceof LivingEntity living)) {
            entity.kill();
            return;
        }
        living.setHealth(0);
        living.kill();
    }

    public static void killAll(List<? extends Entity> entities, Player attacker) {
        entities.forEach(entity -> kill(entity, attacker));
    }
}
