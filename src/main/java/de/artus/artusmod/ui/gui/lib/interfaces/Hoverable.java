package de.artus.artusmod.ui.gui.lib.interfaces;

public interface Hoverable {

    boolean currentlyHovered = false;

    boolean isHovered(int mouseX, int mouseY);

    void onMouseEnter();
    void onMouseLeave();

}
