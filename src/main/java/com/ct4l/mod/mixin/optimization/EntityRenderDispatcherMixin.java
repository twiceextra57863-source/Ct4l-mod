package com.ct4l.mod.mixin.optimization;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    /**
     * Aggressive Entity Culling.
     * Prevents rendering entities that are extremely far away or absolutely not in camera view.
     */
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void aggressiveCull(E entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        // If the entity is outside the render distance bounding box, instantly return false to skip all GPU calls.
        if (!frustum.isVisible(entity.getBoundingBox())) {
            cir.setReturnValue(false);
        }
    }
}