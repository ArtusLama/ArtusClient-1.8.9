package de.artus.artusmod.ui.gui.lib.helpers;

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

        // TODO: Implement custom font
        //setCustomFont(new FontRenderer(settings, new ResourceLocation(ArtusMod.MODID, "fonts/customFont.png"), renderEngine, false));
        //registerFont(getCustomFont());
    }

    public static void registerFont(FontRenderer fontRenderer) {
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(fontRenderer);
    }




}
