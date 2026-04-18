package com.ct4l.mod.mixin.optimization;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class FogRendererMixin {

    /**
     * Disables fog rendering entirely to save massive GPU fragment shader cycles.
     * Highly effective for mobile GPUs (PojavLauncher).
     */
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void optimizeFogRendering(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        // By completely skipping fog calculation, we ease the OpenGL state machine.
        RenderSystem.setShaderFogStart(viewDistance * 2.0F);
        RenderSystem.setShaderFogEnd(viewDistance * 2.5F);
        ci.cancel();
    }
}