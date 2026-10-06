package com.kiroboost.client;

import net.minecraft.client.Minecraft;

public final class KiroBoostOptimizer {

    private KiroBoostOptimizer() {
    }

    /**
     * Comprueba si KiroBoost está usando algún perfil
     * de optimización.
     */
    public static boolean isOptimizationEnabled() {
        return KiroBoostClient.lowEndMode
                || KiroBoostClient.ultraLowEndMode;
    }

    /**
     * Perfil actual.
     */
    public static Mode getMode() {

        if (KiroBoostClient.ultraLowEndMode) {
            return Mode.ULTRA_LOW_END;
        }

        if (KiroBoostClient.lowEndMode) {
            return Mode.LOW_END;
        }

        return Mode.NORMAL;
    }

    /**
     * Distancia de render recomendada por KiroBoost.
     */
    public static int getRecommendedRenderDistance(
            Minecraft client
    ) {

        if (KiroBoostClient.ultraLowEndMode) {
            return Math.min(
                    client.options.renderDistance().get(),
                    6
            );
        }

        if (KiroBoostClient.lowEndMode) {
            return Math.min(
                    client.options.renderDistance().get(),
                    10
            );
        }

        return client.options.renderDistance().get();
    }

    /**
     * Distancia de simulación recomendada.
     */
    public static int getRecommendedSimulationDistance(
            Minecraft client
    ) {

        if (KiroBoostClient.ultraLowEndMode) {
            return Math.min(
                    client.options.simulationDistance().get(),
                    4
            );
        }

        if (KiroBoostClient.lowEndMode) {
            return Math.min(
                    client.options.simulationDistance().get(),
                    6
            );
        }

        return client.options.simulationDistance().get();
    }

    /**
     * Decide si debemos reducir efectos visuales.
     */
    public static boolean reduceVisualEffects() {

        return KiroBoostClient.lowEndMode
                || KiroBoostClient.ultraLowEndMode;
    }

    /**
     * Decide si debemos usar el perfil más agresivo.
     */
    public static boolean isUltraMode() {

        return KiroBoostClient.ultraLowEndMode;
    }

    public enum Mode {

        NORMAL,

        LOW_END,

        ULTRA_LOW_END
    }
}
