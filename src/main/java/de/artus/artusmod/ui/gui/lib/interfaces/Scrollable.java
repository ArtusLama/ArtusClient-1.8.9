package de.artus.artusmod.ui.gui.lib.interfaces;

public interface Scrollable extends Hoverable {


    void onScroll(int scroll);
    int getContentHeight();

    boolean isInScrollableArea(int mouseX, int mouseY);

}
