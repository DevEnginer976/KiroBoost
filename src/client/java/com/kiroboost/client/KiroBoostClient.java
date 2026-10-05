package com.kiroboost.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class KiroBoostClient implements ClientModInitializer {

    private static int tickCounter = 0;

    @Override
    public void onInitializeClient() {
        System.out.println("[KiroBoost] Optimizador iniciado.");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tickCounter++;

            // Ejecutar solamente cada 10 segundos
            if (tickCounter >= 200) {
                tickCounter = 0;
                optimize(client);
            }
        });
    }

    private static void optimize(Minecraft client) {
        if (client.player == null) {
            return;
        }

        // Evitamos ejecutar tareas innecesarias constantemente.
        System.gc();

        System.out.println(
                "[KiroBoost] Optimización ejecutada | Jugador: "
                        + client.player.getName().getString()
        );
    }
}
