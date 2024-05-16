package de.artus.artusmod.ui.gui.lib;

import lombok.Getter;
import lombok.Setter;

public abstract class UiElement {

    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

    @Getter @Setter
    private int width;
    @Getter @Setter
    private int height;

}
