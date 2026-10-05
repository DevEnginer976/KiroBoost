package com.kiroboost.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class KiroBoostClient implements ClientModInitializer {

    private static boolean lowEndMode = false;
    private static boolean ultraLowEndMode = false;

    private static int tickCounter = 0;

    private static KeyMapping lowEndKey;
    private static KeyMapping ultraKey;
    private static KeyMapping statsKey;

    @Override
    public void onInitializeClient() {

        System.out.println("[KiroBoost] ============================");
        System.out.println("[KiroBoost] KiroBoost V2 iniciado.");
        System.out.println("[KiroBoost] ============================");

        /*
         * TECLA LOW-END
         */
        lowEndKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.low_end",
                        GLFW.GLFW_KEY_F8,
                        KeyMapping.Category.MISC
                )
        );

        /*
         * TECLA ULTRA LOW-END
         */
        ultraKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.ultra_low_end",
                        GLFW.GLFW_KEY_F9,
                        KeyMapping.Category.MISC
                )
        );

        /*
         * TECLA ESTADÍSTICAS
         */
        statsKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.kiroboost.stats",
                        GLFW.GLFW_KEY_F7,
                        KeyMapping.Category.MISC
                )
        );

        /*
         * TICK DEL CLIENTE
         */
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            /*
             * F8
             */
            while (lowEndKey.consumeClick()) {

                lowEndMode = !lowEndMode;

                if (lowEndMode) {
                    enableLowEnd(client);
                } else {
                    disableLowEnd(client);
                }
            }

            /*
             * F9
             */
            while (ultraKey.consumeClick()) {

                ultraLowEndMode = !ultraLowEndMode;

                if (ultraLowEndMode) {
                    enableUltraLowEnd(client);
                } else {
                    disableUltraLowEnd(client);
                }
            }

            /*
             * F7
             */
            while (statsKey.consumeClick()) {
                showStats(client);
            }

            /*
             * Estadísticas automáticas cada 10 segundos
             */
            tickCounter++;

            if (tickCounter >= 200) {

                tickCounter = 0;

                printStats(client);
            }
        });
    }

    /*
     * =========================
     * LOW-END
     * =========================
     */

    private static void enableLowEnd(Minecraft client) {

        System.out.println(
                "[KiroBoost] Modo Low-End ACTIVADO."
        );

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(
                            "§a[KiroBoost] §fModo Low-End §aACTIVADO"
                    ),
                    true
            );
        }
    }

    private static void disableLowEnd(Minecraft client) {

        System.out.println(
                "[KiroBoost] Modo Low-End DESACTIVADO."
        );

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(
                            "§c[KiroBoost] §fModo Low-End §cDESACTIVADO"
                    ),
                    true
            );
        }
    }

    /*
     * =========================
     * ULTRA LOW-END
     * =========================
     */

    private static void enableUltraLowEnd(Minecraft client) {

        System.out.println(
                "[KiroBoost] Modo Ultra Low-End ACTIVADO."
        );

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(
                            "§b[KiroBoost] §fUltra Low-End §bACTIVADO"
                    ),
                    true
            );
        }
    }

    private static void disableUltraLowEnd(Minecraft client) {

        System.out.println(
                "[KiroBoost] Modo Ultra Low-End DESACTIVADO."
        );

        if (client.player != null) {

            client.player.displayClientMessage(
                    Component.literal(
                            "§e[KiroBoost] §fUltra Low-End §eDESACTIVADO"
                    ),
                    true
            );
        }
    }

    /*
     * =========================
     * ESTADÍSTICAS
     * =========================
     */

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
                            "§6KiroBoost §8» "
                                    + "§fFPS: §a"
                                    + client.getFps()
                                    + " §8| "
                                    + "§fRAM: §b"
                                    + usedMemory
                                    + "MB"
                                    + " §8/ §b"
                                    + maxMemory
                                    + "MB"
                                    + " §8| "
                                    + "§fLow-End: "
                                    + (lowEndMode
                                    ? "§aON"
                                    : "§cOFF")
                    ),
                    false
            );
        }
    }

    /*
     * =========================
     * LOG
     * =========================
     */

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
                "[KiroBoost] FPS: "
                        + client.getFps()
                        + " | RAM: "
                        + usedMemory
                        + " MB / "
                        + maxMemory
                        + " MB"
                        + " | Low-End: "
                        + (lowEndMode
                        ? "ON"
                        : "OFF")
                        + " | Ultra: "
                        + (ultraLowEndMode
                        ? "ON"
                        : "OFF")
        );
    }
}
