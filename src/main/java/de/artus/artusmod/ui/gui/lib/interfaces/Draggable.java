package de.artus.artusmod.ui.gui.lib.interfaces;

public interface Draggable extends Clickable {

    void onDragStart(int mouseX, int mouseY);
    void onDragEnd();

}
