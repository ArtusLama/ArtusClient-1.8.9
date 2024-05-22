package de.artus.artusmod.ui.gui.lib.interfaces;

import de.artus.artusmod.utils.mouse.MouseButton;

public interface Clickable extends Hoverable {

    void onClick(MouseButton btn);

    default void onMouseDown(MouseButton btn) {
        // empty
    }
    default void onMouseUp(MouseButton btn) {
        // empty
    }

}
