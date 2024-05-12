package de.artus.artusmod.ui.gui.screens.menus;


import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.ClientSettingsMod;
import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.lib.components.mods.ModMenuMod;
import de.artus.artusmod.ui.gui.lib.components.mods.ModMenuModsList;
import de.artus.artusmod.ui.gui.lib.components.mods.ModMenuTopBar;
import de.artus.artusmod.ui.gui.lib.containers.ListDirection;
import de.artus.artusmod.ui.gui.lib.containers.ScrollableListContainer;
import de.artus.artusmod.ui.gui.lib.shapes.RoundedRectShape;
import de.artus.artusmod.ui.gui.screens.AScreen;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;


public class ModMenuScreen extends AScreen {


    @Getter @Setter
    private Mod selectedMod;


    @Override
    public void init() {
        setSelectedMod(ArtusMod.getModManager().getClientSettingsMod());

        int padding = 4;
        int bigPadding = padding * 2;
        int modSettingsScreenWidth = (int) (this.width / 1.5);
        int center = this.width / 2;
        int modItemSize = this.height / 9;

        int topBottomPadding = this.height / 8;

        getDrawables().add(new ModMenuModsList(center - modSettingsScreenWidth / 2 + bigPadding - modItemSize, topBottomPadding, modItemSize, this.height - topBottomPadding * 2, padding, modItemSize));
        getDrawables().add(new ModMenuTopBar(center - modSettingsScreenWidth / 2 + modItemSize - bigPadding, topBottomPadding, modSettingsScreenWidth, modItemSize, 6));
        getDrawables().add(new RoundedRectShape(center - modSettingsScreenWidth / 2 + modItemSize - bigPadding, topBottomPadding + modItemSize + padding, modSettingsScreenWidth, this.height - topBottomPadding * 2 - modItemSize - padding, 6));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);
    }

    public void openModScreen(Mod mod) {
        setSelectedMod(mod);
    }
}
