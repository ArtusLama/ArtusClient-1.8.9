package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.gui.lib.components.interactable.BasicButton;
import de.artus.artusmod.ui.gui.lib.components.interactable.BasicTextButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenuScreen extends AScreen {


    @Override
    public void init() {
        int btnWidth = 175;
        int btnHeight = 20;
        int btnYOffset = 25;

        getElements().add(
                new BasicTextButton(
                        width / 2 - btnWidth / 2,
                        height / 2 - btnHeight,
                        btnWidth,
                        btnHeight,
                        "Singleplayer"
                ).setOnClick(() -> Minecraft.getMinecraft().displayGuiScreen(new GuiSelectWorld(this)))
        );
        getElements().add(
                new BasicTextButton(
                        width / 2 - btnWidth / 2,
                        height / 2 - btnHeight + btnYOffset,
                        btnWidth,
                        btnHeight,
                        "Multiplayer"
                ).setOnClick(() -> Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this)))
        );
        getElements().add(
                new BasicTextButton(
                        width / 2 - btnWidth / 2,
                        height / 2 - btnHeight + (2 * btnYOffset),
                        btnWidth,
                        btnHeight,
                        "Options"
                ).setOnClick(() ->Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(this, this.mc.gameSettings))) //TODO: implement options screen)
        );


        //TODO for dev only (all components screen for testing) also handle screen switching better!
        getElements().add(
                new BasicButton(5, 5, 20, 20).setOnClick(() -> Minecraft.getMinecraft().displayGuiScreen(new AllComponentsScreen()))
        );

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
