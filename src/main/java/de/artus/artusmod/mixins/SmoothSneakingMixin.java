package de.artus.artusmod.mixins;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.fixes.SmoothSneakAnimationMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class SmoothSneakingMixin {

    @Unique
    float sneakHeight;
    @Unique
    float prevSneakHeight;

    @Unique
    float finalSneakHeight;

    @Inject(method = "setupCameraTransform", at = @At("HEAD"))
    public void getInterpolatedEyeHeight(float partialTicks, int pass, CallbackInfo ci) {
        if (ArtusMod.getModManager().isModEnabled(SmoothSneakAnimationMod.class)) {
            finalSneakHeight = prevSneakHeight + (sneakHeight - prevSneakHeight) * partialTicks;
        }
    }

    @Redirect(method = "orientCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getEyeHeight()F"))
    public float modifyEyeHeight(Entity entity, float partialTicks) {
        return ArtusMod.getModManager().isModEnabled(SmoothSneakAnimationMod.class) ?
                finalSneakHeight : entity.getEyeHeight();

    }

    @Inject(method = "updateRenderer", at = @At("HEAD"))
    private void interpolateHeight(CallbackInfo ci) {
        if (ArtusMod.getModManager().isModEnabled(SmoothSneakAnimationMod.class)) {
            Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
            float eyeHeight = entity.getEyeHeight();

            if (entity.isSneaking()) eyeHeight -= 0.1f; // deeper sneaking [optional]

            prevSneakHeight = sneakHeight;

            float speed = eyeHeight < sneakHeight ? 0.6f : 0.8f;
            sneakHeight += (eyeHeight - sneakHeight) * speed;
        }

    }


}
