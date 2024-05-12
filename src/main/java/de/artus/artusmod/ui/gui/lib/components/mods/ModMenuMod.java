package de.artus.artusmod.ui.gui.lib.components.mods;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.lib.Clickable;
import de.artus.artusmod.ui.gui.lib.interactive.HoverableRoundedRect;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class ModMenuMod extends HoverableRoundedRect implements Clickable {

    @Getter @Setter
    private Mod mod;

    public ModMenuMod(Mod mod, int width, int height) {
        super(0, 0, width, height, 6);
        setMod(mod);
        setBackgroundColor(getTheme().getModMenuModBackgroundColor());
        setCurrentBackgroundColor(getBackgroundColor());
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);

        boolean isSelected = ArtusMod.getModMenuScreen().getSelectedMod() == getMod();
        int iconPadding = 4;

        GL11.glColor4f(1, 1, 1, isSelected ? 1 : 0.2f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(getMod().getIcon());
        drawScaledCustomSizeModalRect(getX() + iconPadding, getY() + iconPadding, 0, 0, 128, 128, getWidth() - iconPadding * 2, getHeight() - iconPadding * 2, 128, 128);
        GlStateManager.resetColor();
    }

    @Override
    public void onHovered(int mouseX, int mouseY) {
        if (ArtusMod.getModMenuScreen().getSelectedMod() != getMod())
            setCurrentBackgroundColor(getTheme().getModMenuModHoverBackgroundColor());
    }

    @Override
    public void whileNotHovered() {
        boolean isSelected = ArtusMod.getModMenuScreen().getSelectedMod() == getMod();
        if (isSelected) {
            setCurrentBackgroundColor(getTheme().getModMenuModSelectedBackgroundColor());
        } else {
            setCurrentBackgroundColor(getTheme().getModMenuModBackgroundColor());
        }
    }

    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {
        if (ArtusMod.getModMenuScreen().getSelectedMod() != getMod()) {
            ArtusMod.getModMenuScreen().openModScreen(getMod());
        }
    }
}
