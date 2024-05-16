package de.artus.artusmod.ui.gui.OLD.lib.components.mods.modInfo;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.OLD.lib.containers.ScrollableListContainer;
import de.artus.artusmod.ui.gui.OLD.lib.shapes.RoundedRectShape;
import de.artus.artusmod.utils.render.ScissorHelper;
import lombok.Getter;

public class ModWindow extends ScrollableListContainer {


    @Getter
    private Mod mod;


    public ModWindow(int x, int y, int width, int height, Mod mod) {
        super(x, y, width, height);
        this.mod = mod;
        setPadding(20);

        // height is flexible (depending on its content)
        getItems().add(new ModInfoSection(0, 0, width, height, getMod()));
    }


    @Override
    public void startClipping() {
        RoundedRectShape bg = ArtusMod.getModMenuScreen().getModWindowBg();
        ScissorHelper.startScissor(bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight());
    }
}
