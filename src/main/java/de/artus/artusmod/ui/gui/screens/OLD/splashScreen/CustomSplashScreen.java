package de.artus.artusmod.ui.gui.screens.OLD.splashScreen;

import de.artus.artusmod.ArtusMod;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import java.util.Optional;


public class CustomSplashScreen {

    @Getter @Setter
    private ResourceLocation splashScreenBackground;

    @Getter @Setter
    private SplashProgress splashProgress;

    public CustomSplashScreen(int progressSteps) {
        setSplashProgress(new SplashProgress(progressSteps));
    }



    public void drawSplashScreen(TextureManager textureManager) {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaledFactor = scaledresolution.getScaleFactor();

        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * scaledFactor, scaledresolution.getScaledHeight() * scaledFactor, true);
        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);

        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if (getSplashScreenBackground() == null)
            setSplashScreenBackground(new ResourceLocation(ArtusMod.MODID, "SplashScreen.png"));

        textureManager.bindTexture(getSplashScreenBackground());

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 1920, 1080);
        drawProgress();

        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * scaledFactor, scaledresolution.getScaledHeight() * scaledFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        Minecraft.getMinecraft().updateDisplay();
    }

    private void drawProgress() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        // TODO switch to rounded rect
        float progress = getSplashProgress().getCurrentStep() / getSplashProgress().getTotalSteps();
        int height = 3;
        Gui.drawRect(0, sr.getScaledHeight() - height, (int) (progress * sr.getScaledWidth()), sr.getScaledHeight(), ArtusMod.getGuiConfiguration().getTheme().getPrimaryColor());

        GlStateManager.resetColor();

        String text = getSplashProgress().getCurrentStep() + "/" + getSplashProgress().getTotalSteps() + " " + getSplashProgress().getCurrentStepName();
        getFontRenderer().ifPresent(fr -> fr.drawString(text, 20, sr.getScaledHeight() - 20, 0xAAAAAA));
    }


    private Optional<FontRenderer> getFontRenderer() {
        if (Minecraft.getMinecraft().fontRendererObj == null) {
            return Optional.empty();
        }
        return Optional.of(Minecraft.getMinecraft().fontRendererObj);
    }


    public void update() {
        drawSplashScreen(Minecraft.getMinecraft().getTextureManager());
    }
}
