package com.kiroboost.client;

public final class KiroBoostParticles {

    private KiroBoostParticles() {
    }

    /**
     * Indica si KiroBoost debe reducir partículas.
     */
    public static boolean shouldReduceParticles() {

        return KiroBoostClient.lowEndMode
                || KiroBoostClient.ultraLowEndMode;
    }

    /**
     * Indica si estamos en el modo más agresivo.
     */
    public static boolean shouldReduceAggressively() {

        return KiroBoostClient.ultraLowEndMode;
    }

    /**
     * Probabilidad aproximada de permitir un efecto.
     *
     * 1.0 = permitir todos.
     * 0.5 = permitir aproximadamente la mitad.
     * 0.25 = permitir aproximadamente una cuarta parte.
     */
    public static double getParticleMultiplier() {

        if (KiroBoostClient.ultraLowEndMode) {
            return 0.25D;
        }

        if (KiroBoostClient.lowEndMode) {
            return 0.50D;
        }

        return 1.0D;
    }
}
