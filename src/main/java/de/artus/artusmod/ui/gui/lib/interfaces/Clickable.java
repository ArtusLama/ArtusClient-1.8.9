package de.artus.artusmod.ui.gui.lib.interfaces;

import de.artus.artusmod.utils.mouse.MouseButton;

public interface Clickable extends Hoverable {

    void onClick(MouseButton btn);
    void onMouseDown(MouseButton btn);
    void onMouseUp(MouseButton btn);

}
