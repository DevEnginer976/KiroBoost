package com.kiroboost.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class KiroBoostClient implements ClientModInitializer {

    public static boolean lowEndMode = false;
    public static boolean ultraLowEndMode = false;

    private static int tickCounter = 0;

    private static KeyMapping lowEndKey;
    private static KeyMapping ultraKey;
    private static KeyMapping statsKey;

    @Override
    public void onInitializeClient() {

        System.out.println("[KiroBoost] ============================");
        System.out.println("[KiroBoost] KiroBoost V2 iniciado.");
        System.out.println("[KiroBoost] ============================");

        lowEndKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.low_end",
                        GLFW.GLFW_KEY_F8,
                        KeyMapping.Category.MISC
                )
        );

        ultraKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.ultra_low_end",
                        GLFW.GLFW_KEY_F9,
                        KeyMapping.Category.MISC
                )
        );

        statsKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.stats",
                        GLFW.GLFW_KEY_F7,
                        KeyMapping.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (lowEndKey.consumeClick()) {
                toggleLowEnd(client);
            }

            while (ultraKey.consumeClick()) {
                toggleUltraLowEnd(client);
            }

            while (statsKey.consumeClick()) {
                showStats(client);
            }

            tickCounter++;

            if (tickCounter >= 200) {
                tickCounter = 0;
                printStats(client);
            }
        });
    }

    private static void toggleLowEnd(Minecraft client) {

        lowEndMode = !lowEndMode;

        if (lowEndMode) {

            ultraLowEndMode = false;

            notify(
                    client,
                    "§a[KiroBoost] §fLow-End §aACTIVADO"
            );

            System.out.println(
                    "[KiroBoost] Low-End ACTIVADO"
            );

        } else {

            notify(
                    client,
                    "§c[KiroBoost] §fLow-End §cDESACTIVADO"
            );

            System.out.println(
                    "[KiroBoost] Low-End DESACTIVADO"
            );
        }
    }

    private static void toggleUltraLowEnd(Minecraft client) {

        ultraLowEndMode = !ultraLowEndMode;

        if (ultraLowEndMode) {

            lowEndMode = false;

            notify(
                    client,
                    "§b[KiroBoost] §fUltra Low-End §bACTIVADO"
            );

            System.out.println(
                    "[KiroBoost] Ultra Low-End ACTIVADO"
            );

        } else {

            notify(
                    client,
                    "§e[KiroBoost] §fUltra Low-End §eDESACTIVADO"
            );

            System.out.println(
                    "[KiroBoost] Ultra Low-End DESACTIVADO"
            );
        }
    }

    private static void showStats(Minecraft client) {

        Runtime runtime = Runtime.getRuntime();

        long maxMemory =
                runtime.maxMemory() / 1024 / 1024;

        long totalMemory =
                runtime.totalMemory() / 1024 / 1024;

        long freeMemory =
                runtime.freeMemory() / 1024 / 1024;

        long usedMemory =
                totalMemory - freeMemory;

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(
                            "§6§lKiroBoost §8» "
                                    + "§fFPS §a"
                                    + client.getFps()
                                    + " §8| "
                                    + "§fRAM §b"
                                    + usedMemory
                                    + "MB"
                                    + "§7/"
                                    + maxMemory
                                    + "MB"
                                    + " §8| "
                                    + "§fModo "
                                    + getModeName()
                    ),
                    false
            );
        }
    }

    private static void notify(
            Minecraft client,
            String message
    ) {

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(message),
                    true
            );
        }
    }

    private static String getModeName() {

        if (ultraLowEndMode) {
            return "§bUltra Low-End";
        }

        if (lowEndMode) {
            return "§aLow-End";
        }

        return "§7Normal";
    }

    private static void printStats(Minecraft client) {

        Runtime runtime = Runtime.getRuntime();

        long maxMemory =
                runtime.maxMemory() / 1024 / 1024;

        long totalMemory =
                runtime.totalMemory() / 1024 / 1024;

        long freeMemory =
                runtime.freeMemory() / 1024 / 1024;

        long usedMemory =
                totalMemory - freeMemory;

        System.out.println(
                "[KiroBoost] FPS="
                        + client.getFps()
                        + " | RAM="
                        + usedMemory
                        + "MB/"
                        + maxMemory
                        + "MB"
                        + " | MODE="
                        + getModeName()
        );
    }
}
