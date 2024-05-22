package de.artus.artusmod.ui.gui.lib.components.text;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;

public class CenteredTextContainer extends TextContainer {

    public CenteredTextContainer(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    @Override
    public void draw() {
        if (isClipToWidth()) {
            DrawHelper.drawCenteredTextBlock(getFontRenderer(), getText(), getLinePadding(), getX(), getY(), getWidth(), getHeight(), getColor());
        } else {
            DrawHelper.drawCenteredString(getFontRenderer(), getText(), getX(), getY(), getColor());
        }
    }
}
