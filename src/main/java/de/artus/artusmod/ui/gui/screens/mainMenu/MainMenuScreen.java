package de.artus.artusmod.ui.gui.screens.mainMenu;


import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.components.ExitButton;
import de.artus.artusmod.ui.gui.lib.containers.ListContainer;
import de.artus.artusmod.ui.gui.lib.containers.ListDirection;
import de.artus.artusmod.ui.gui.lib.interactive.HoverableRoundedRect;
import de.artus.artusmod.ui.gui.lib.interactive.RoundedButton;
import de.artus.artusmod.ui.gui.screens.AScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;


public class MainMenuScreen extends AScreen {


    @Override
    public void initGui() {
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

        int exitButtonPadding = 5;
        int exitButtonSize = 15;
        // Exit button
        getDrawables().add(new ExitButton(this.width - exitButtonSize - exitButtonPadding, exitButtonPadding, exitButtonSize, exitButtonSize).setOnClick(mouseButton -> {
            if (mouseButton == 0)
                Minecraft.getMinecraft().shutdown();
        }));

        List<Drawable> listItems = new ArrayList<>();
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 5).setBackgroundColor(getGuiConfiguration().getTheme().getPrimaryColor()));
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 4));
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 8).setBackgroundColor(getGuiConfiguration().getTheme().getPrimaryColor()));
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 5));
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 2).setBackgroundColor(getGuiConfiguration().getTheme().getPrimaryColor()));
        listItems.add(new HoverableRoundedRect(0, 0, 30, 20, 1).setBackgroundColor(getGuiConfiguration().getTheme().getPrimaryColor()));
        ListContainer listContainer = new ListContainer(10, 10, this.width / 2, 50, listItems);
        listContainer.setListDirection(ListDirection.HORIZONTAL);
        getDrawables().add(listContainer);
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("artusmod/MainMenuBackground.png"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, this.width, this.height, 1920, 1080);
        GlStateManager.enableAlpha();
        super.drawScreen(mouseX, mouseY, particalTicks);
    }


}
