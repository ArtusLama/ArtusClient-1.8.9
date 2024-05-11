package de.artus.artusmod.ui.gui.lib;

public interface Hoverable {

    boolean isHovered(int mouseX, int mouseY);

    void onHovered(int mouseX, int mouseY);
    void whileNotHovered();

}
