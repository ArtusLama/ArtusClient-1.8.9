package de.artus.artusmod.mixins.splashScreen;

import de.artus.artusmod.ArtusMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(Minecraft.class)
public abstract class LoadCustomSplashScreen {


    @Shadow public FontRenderer fontRendererObj;

    /**
     * @author artus_dev
     * @reason load custom splash screen
     */
    @Overwrite
    public void drawSplashScreen(TextureManager p_drawSplashScreen_1_) {
        ArtusMod.loadSplashScreen();
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/IReloadableResourceManager;registerReloadListener(Lnet/minecraft/client/resources/IResourceManagerReloadListener;)V"))
    public void reloadSplashScreen(CallbackInfo ci) {
        if (fontRendererObj != null)
            ArtusMod.getCustomSplashScreen().update();
    }



}
