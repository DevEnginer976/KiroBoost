package com.kiroboost.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.language.I18n;
import org.lwjgl.glfw.GLFW;

public class KiroBoostClient implements ClientModInitializer {

    private static boolean lowEndMode = false;
    private static int tickCounter = 0;

    private static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {

        System.out.println("[KiroBoost] KiroBoost V1.1 iniciado.");

        toggleKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.toggle",
                        GLFW.GLFW_KEY_F8,
                        "category.kiroboost"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (toggleKey.consumeClick()) {
                lowEndMode = !lowEndMode;

                if (lowEndMode) {
                    enableLowEnd(client);
                } else {
                    disableLowEnd(client);
                }
            }

            tickCounter++;

            if (tickCounter >= 200) {
                tickCounter = 0;
                printStats(client);
            }
        });
    }

    private static void enableLowEnd(Minecraft client) {
        System.out.println("[KiroBoost] Modo Low-End ACTIVADO.");

        if (client.player != null) {
            client.player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                            "§a[KiroBoost] §fModo Low-End §aACTIVADO"
                    ),
                    true
            );
        }
    }

    private static void disableLowEnd(Minecraft client) {
        System.out.println("[KiroBoost] Modo Low-End DESACTIVADO.");

        if (client.player != null) {
            client.player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                            "§c[KiroBoost] §fModo Low-End §cDESACTIVADO"
                    ),
                    true
            );
        }
    }

    private static void printStats(Minecraft client) {

        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;

        System.out.println(
                "[KiroBoost] FPS: "
                        + client.getFps()
                        + " | RAM: "
                        + usedMemory
                        + " MB / "
                        + maxMemory
                        + " MB"
                        + " | Low-End: "
                        + (lowEndMode ? "ON" : "OFF")
        );
    }
}
