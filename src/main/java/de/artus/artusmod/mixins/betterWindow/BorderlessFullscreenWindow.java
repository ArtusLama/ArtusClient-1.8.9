package de.artus.artusmod.mixins.betterWindow;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class BorderlessFullscreenWindow {


    @Shadow private boolean fullscreen;

    @Getter @Setter
    private boolean lastFullscreen = false;


    @Inject(method = "toggleFullscreen", at = @At("RETURN"))
    private void onSetFullscreen(CallbackInfo ci) {
        checkFullscreen();
    }
    @Inject(method = "setInitialDisplayMode", at = @At("RETURN"))
    private void onSetInitialDisplayMode(CallbackInfo ci) {
        checkFullscreen();
    }

    private void checkFullscreen() {
        if (isLastFullscreen() != this.fullscreen) {
            setLastFullscreen(fullscreen);
            fix(fullscreen);
        }
    }



    private void fix(boolean fullscreen) {
        try {
            if (fullscreen) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
                Display.setDisplayMode(Display.getDesktopDisplayMode());
                Display.setLocation(0, 0);
                Display.setFullscreen(false);
                Display.setResizable(false);
            } else {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
                DisplayMode desktop = Display.getDesktopDisplayMode();
                int width = Minecraft.getMinecraft().displayWidth;
                int height = Minecraft.getMinecraft().displayHeight;
                Display.setLocation(desktop.getWidth() / 2 - width / 2, desktop.getHeight() / 2 - height / 2);
                Display.setDisplayMode(new DisplayMode(width, height));
                Display.setResizable(true);
            }
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }


}
