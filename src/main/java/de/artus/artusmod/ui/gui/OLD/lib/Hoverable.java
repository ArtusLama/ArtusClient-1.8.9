package de.artus.artusmod.ui.gui.OLD.lib;

public interface Hoverable {

    boolean isHovered(int mouseX, int mouseY);

    void onHovered(int mouseX, int mouseY);
    void whileNotHovered();

}
