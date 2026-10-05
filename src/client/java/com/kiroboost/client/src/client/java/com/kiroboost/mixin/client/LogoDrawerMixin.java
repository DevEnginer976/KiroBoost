package com.kiroboost.mixin.client;

import net.minecraft.client.font.Font;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {

    @Inject(
            method = "draw(Lnet/minecraft/client/gui/DrawContext;IF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void kiroboost$drawMineKiro(
            DrawContext context,
            int screenWidth,
            float alpha,
            CallbackInfo ci
    ) {
        Font font = net.minecraft.client.Minecraft.getInstance().font;

        int y = 30;

        // Sombra
        context.drawCenteredTextWithShadow(
                font,
                "MineKiro",
                screenWidth / 2 + 2,
                y + 2,
                0xFF202020
        );

        // Logo principal
        context.drawCenteredTextWithShadow(
                font,
                "MineKiro",
                screenWidth / 2,
                y,
                0xFFFFFFFF
        );

        ci.cancel();
    }
}
