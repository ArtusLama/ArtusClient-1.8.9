package de.artus.artusmod.mixins;

import de.artus.artusmod.ui.gui.screens.menus.MainMenuScreenOLD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(Minecraft.class)
public abstract class CustomMainMenu {


    @ModifyVariable(method = "displayGuiScreen", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiScreen displayGuiScreen(GuiScreen p_displayGuiScreen_1_) {
        if (p_displayGuiScreen_1_ instanceof GuiMainMenu) {
            return new MainMenuScreenOLD();
        }
        return p_displayGuiScreen_1_;
    }

    @ModifyVariable(method = "displayGuiScreen", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/GuiMainMenu;<init>()V"), argsOnly = true)
    private GuiScreen displayGuiScreen2(GuiScreen value) {
        return value instanceof GuiMainMenu ? new MainMenuScreenOLD() : value;
    }




}
