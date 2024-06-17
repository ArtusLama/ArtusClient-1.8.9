package de.artus.artusmod.ui.gui.lib.interfaces;

import de.artus.artusmod.ui.gui.lib.helpers.TooltipHelper;

public interface Tooltip extends Hoverable {


    String getTooltipText();

    // In mills
    default int showTooltipAfterDelay() { // TODO: implement this
        return 1000;
    }

    default boolean checkTooltipDelay(long beginTime) {
        return System.currentTimeMillis() - showTooltipAfterDelay() > beginTime;
    }

    default void displayTooltip(int x, int y) {
        TooltipHelper.drawTooltip(x, y, getTooltipText(), TooltipHelper.getTooltipFontRenderer());
    }

}
