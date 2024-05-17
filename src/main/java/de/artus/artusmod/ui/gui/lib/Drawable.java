package de.artus.artusmod.ui.gui.lib;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.Theme;
import lombok.Getter;
import lombok.Setter;


public abstract class Drawable extends UiElement {

    @Getter @Setter
    private Theme theme = ArtusMod.getGuiConfiguration().getTheme();

    public Drawable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public abstract void draw();

}
