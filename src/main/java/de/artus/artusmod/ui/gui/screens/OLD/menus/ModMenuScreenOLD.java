package de.artus.artusmod.ui.gui.screens.OLD.menus;


import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.OLD.lib.components.mods.ModMenuModsList;
import de.artus.artusmod.ui.gui.OLD.lib.components.mods.ModMenuTopBar;
import de.artus.artusmod.ui.gui.OLD.lib.components.mods.modInfo.ModWindow;
import de.artus.artusmod.ui.gui.OLD.lib.shapes.RoundedRectShape;
import de.artus.artusmod.ui.gui.screens.OLD.AScreenOLD;
import lombok.Getter;
import lombok.Setter;



public class ModMenuScreenOLD extends AScreenOLD {


    @Getter @Setter
    private Mod selectedMod;

    @Getter @Setter
    private ModWindow modWindow;
    @Getter @Setter
    private RoundedRectShape modWindowBg;


    @Override
    public void init() {
        setSelectedMod(ArtusMod.getModManager().getClientSettingsMod());

        int padding = 4;
        int bigPadding = padding * 2;
        int modSettingsScreenWidth = (int) (this.width / 1.5);
        int center = this.width / 2;
        int modItemSize = this.height / 9;

        int topBottomPadding = this.height / 8;
        int modWindowPadding = 8;

        getDrawables().add(new ModMenuModsList(center - modSettingsScreenWidth / 2 + bigPadding - modItemSize, topBottomPadding, modItemSize, this.height - topBottomPadding * 2, padding, modItemSize));
        getDrawables().add(new ModMenuTopBar(center - modSettingsScreenWidth / 2 + modItemSize - bigPadding, topBottomPadding, modSettingsScreenWidth, modItemSize, 6));

        RoundedRectShape modWindowBg = new RoundedRectShape(center - modSettingsScreenWidth / 2 + modItemSize - bigPadding, topBottomPadding + modItemSize + padding, modSettingsScreenWidth, this.height - topBottomPadding * 2 - modItemSize - padding, 6);
        getDrawables().add(modWindowBg);
        setModWindowBg(modWindowBg);
        setModWindow(new ModWindow(modWindowBg.getX() + modWindowPadding, modWindowBg.getY() + modWindowPadding, modWindowBg.getWidth() - modWindowPadding * 2, modWindowBg.getHeight() - modWindowPadding * 2, getSelectedMod()));
        getDrawables().add(getModWindow());

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);
    }

    public void openModScreen(Mod mod) {
        ModWindow oldModWindow = getModWindow();
        setModWindow(new ModWindow(oldModWindow.getX(), oldModWindow.getY(), oldModWindow.getWidth(), oldModWindow.getHeight(), mod));
        getDrawables().removeIf(d -> d instanceof ModWindow);
        getDrawables().add(getModWindow());

        setSelectedMod(mod);
    }
}
