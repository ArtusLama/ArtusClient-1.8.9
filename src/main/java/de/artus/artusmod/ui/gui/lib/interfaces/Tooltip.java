package de.artus.artusmod.ui.gui.lib.interfaces;

import de.artus.artusmod.ui.gui.lib.helpers.TooltipHelper;

public interface Tooltip extends Hoverable {

    String getTooltipText();

    default int showTooltipAfterDelay() { // TODO: implement this
        return 2000;
    }

    default void displayTooltip(int x, int y) {
        TooltipHelper.drawTooltip(x, y, getTooltipText(), TooltipHelper.getTooltipFontRenderer());
    }

}
