package de.artus.artusmod.mixins;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.fixes.FixHitDelayMod;
import de.artus.artusmod.mods.fixes.SmoothSneakAnimationMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class HitDelayFix {


    @Shadow private int leftClickCounter;

    @Inject(method = "clickMouse", at = @At("RETURN"))
    private void clickMouse(CallbackInfo ci) {
        if (ArtusMod.getModManager().isModEnabled(FixHitDelayMod.class)) {
            this.leftClickCounter = 0;
        }
    }

}
