package de.artus.artusmod.ui.gui.lib.font;

import de.artus.artusmod.ArtusMod;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;


public class FontManager {

    @Getter @Setter
    private static FontRenderer normalMCFont;

    @Getter @Setter
    private static FontRenderer customFont;

    public static void loadFonts() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;


        // Already registered by minecraft
        setNormalMCFont(Minecraft.getMinecraft().fontRendererObj);

        setCustomFont(getNormalMCFont());

        try {
            setCustomFont(new CustomFont(settings, new ResourceLocation(ArtusMod.MODID, "fonts/Inter.ttf"), renderEngine, false));
        } catch (Exception e) {
            e.printStackTrace();
            setCustomFont(getNormalMCFont()); // Font fallback -> use default minecraft font
        }

        registerFont(getCustomFont());
    }

    public static void registerFont(FontRenderer fontRenderer) {
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(fontRenderer);
    }




}
