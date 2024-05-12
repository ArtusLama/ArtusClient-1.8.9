package de.artus.artusmod.ui.gui.lib.components.mods;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.lib.Clickable;
import de.artus.artusmod.ui.gui.lib.interactive.HoverableRoundedRect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModMenuTopBar extends HoverableRoundedRect implements Clickable {


    public ModMenuTopBar(int x, int y, int width, int height, int radius) {
        super(x, y, width, height, radius);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);

        Mod selectedMod = ArtusMod.getModMenuScreen().getSelectedMod();
        String modName = selectedMod.getName();
        ResourceLocation icon = selectedMod.getIcon();

        int iconPadding = 6;
        int iconSize = getHeight() - iconPadding * 2;

        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
        drawScaledCustomSizeModalRect(getX() + iconPadding, getY() + iconPadding, 0, 0, 128, 128, iconSize, iconSize, 128, 128);
        GlStateManager.resetColor();

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        drawString(fr, modName, getX() + iconSize + iconPadding * 2, getY() + getHeight() / 2 - fr.FONT_HEIGHT / 2, getTheme().getTextColor());
    }

    @Override
    public void onHovered(int mouseX, int mouseY) {}

    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {

    }
}
