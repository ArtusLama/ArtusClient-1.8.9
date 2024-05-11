package de.artus.artusmod.mixins.splashScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraftforge.fml.client.SplashProgress;
import org.lwjgl.LWJGLException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SplashProgress.class)
public abstract class DisableForgeSplashScreen {


    /**
     * @author artus_dev
     * @reason Disable Forge's splash screen
     */
    @Overwrite
    public static void start() {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void drawVanillaScreen(TextureManager renderEngine) throws LWJGLException {
        Minecraft.getMinecraft().drawSplashScreen(renderEngine);
    }

}
