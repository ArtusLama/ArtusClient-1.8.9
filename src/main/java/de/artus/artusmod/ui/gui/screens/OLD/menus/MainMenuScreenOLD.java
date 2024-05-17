package de.artus.artusmod.ui.gui.screens.OLD.menus;


import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.gui.OLD.lib.components.ExitButton;
import de.artus.artusmod.ui.gui.OLD.lib.interactive.RoundedButton;
import de.artus.artusmod.ui.gui.lib.DrawHelper;
import de.artus.artusmod.ui.gui.screens.AllComponentsScreen;
import de.artus.artusmod.ui.gui.screens.OLD.AScreenOLD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;


public class MainMenuScreenOLD extends AScreenOLD {


    @Override
    public void init() {
        getDrawables().clear();

        int buttonWidth = 175;
        int buttonHeight = 20;

        int middleButtonX = (this.width / 2) - buttonWidth / 2;
        int startY = this.height / 2 - buttonHeight;

        // Default buttons
        getDrawables().add(new RoundedButton(middleButtonX, startY, buttonWidth, buttonHeight, I18n.format("menu.singleplayer")).setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().displayGuiScreen(new GuiSelectWorld(this));
        }));
        getDrawables().add(new RoundedButton(middleButtonX, startY + buttonHeight + 5, buttonWidth, buttonHeight, I18n.format("menu.multiplayer")).setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));
        }));
        getDrawables().add(new RoundedButton(middleButtonX, startY + buttonHeight * 2 + 10, buttonWidth, buttonHeight, I18n.format("menu.options")).setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }));
        getDrawables().add(new RoundedButton(middleButtonX, startY + buttonHeight * 3 + 40, buttonWidth, buttonHeight, "ALL NEW UI COMPONENTS").setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().displayGuiScreen(new AllComponentsScreen());
        }));

        int exitButtonPadding = 5;
        int exitButtonSize = 15;
        // Exit button
        getDrawables().add(new ExitButton(this.width - exitButtonSize - exitButtonPadding, exitButtonPadding, exitButtonSize, exitButtonSize).setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().shutdown();
        }));
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ArtusMod.MODID, "MainMenuBackground.png"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, this.width, this.height, 1920, 1080);
        GlStateManager.enableAlpha();

        super.drawScreen(mouseX, mouseY, particalTicks);
    }


}
