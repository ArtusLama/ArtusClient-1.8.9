package de.artus.artusmod.ui.gui.lib.font;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


@Getter
public class CustomFont extends FontRenderer {


    private final Font font;
    private final TTFFontRenderer renderer;


    public CustomFont(GameSettings gameSettings, ResourceLocation fontFile, TextureManager textureManager, boolean unicodeFlag) throws IOException, FontFormatException {
        // calling super with dummy font
        super(gameSettings, new ResourceLocation("textures/font/ascii.png"), textureManager, unicodeFlag);


        // Load the TTF font
        try {
            IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
            InputStream fontStream = resourceManager.getResource(fontFile).getInputStream();
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 14);
            renderer = new TTFFontRenderer(font);
            FONT_HEIGHT = renderer.getFont().getLineHeight() / 2 - 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int drawString(String text, float x, float y, int color, boolean drawShadow) {
        if (drawShadow) {
            return getRenderer().drawStringWithShadow(text, x, y, color);
        } else {
            return getRenderer().drawString(text, x, y, color);
        }
    }

    @Override
    public int getStringWidth(String p_getStringWidth_1_) {
        return (int) getRenderer().getWidth(p_getStringWidth_1_);
    }



}
