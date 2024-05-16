package de.artus.artusmod.ui.gui.OLD.lib;

import lombok.*;
import lombok.experimental.Accessors;

public abstract class BackgroundDrawable extends Drawable {


    @Accessors(chain = true)
    @Getter @Setter
    private int backgroundColor = getTheme().getBackgroundColor();

    @Accessors(chain = true)
    @Getter @Setter
    private int currentBackgroundColor = getBackgroundColor();

    public BackgroundDrawable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }



}
