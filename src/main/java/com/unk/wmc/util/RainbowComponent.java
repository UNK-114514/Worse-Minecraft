package com.unk.wmc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;

import java.awt.*;

public class RainbowComponent {
    public static Component of(String s, float saturation, float brightness, float speed, int offset) {
        Level level = Minecraft.getInstance().level;
        if (level == null) return Component.literal(s);

        MutableComponent result = Component.empty();
        long time = level.getGameTime();

        for (int i = 0; i < s.length(); i++) {
            float hue = ((time + offset) * speed + i * 0.05f) % 1f;
//            float hue = ((time * speed + i * 0.05f) % 1f + 1f) % 1f;
            int color = Color.HSBtoRGB(hue, saturation, brightness);
            result.append(Component.literal(String.valueOf(s.charAt(i))).withColor(color));
        }

        return result;
    }

    public static Component of(String s) {
        return of(s, 0.75F, 1F, 0.01F, 0);
    }
}