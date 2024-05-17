package de.artus.artusmod.ui.gui.lib.interfaces;

public interface Hoverable {


    boolean isHovered(int mouseX, int mouseY);

    void setCurrentlyHovered(boolean currentlyHovered);
    boolean isCurrentlyHovered();

    void onMouseEnter();
    void onMouseLeave();

    default boolean doesChangeCursor() {
        return true;
    }

}
