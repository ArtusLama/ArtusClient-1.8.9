package de.artus.artusmod.ui.gui.lib.components.interactable;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.helpers.FontManager;
import lombok.Getter;
import lombok.Setter;

public class BasicTextButton extends BasicButton {

    @Getter @Setter
    private String text;

    public BasicTextButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        setText(text);
    }

    @Override
    public void draw() {
        super.draw();
        DrawHelper.drawCenteredString(
                FontManager.getCustomFont(),
                getText(),
                getX() + getWidth() / 2,
                getY() + getHeight() / 2 - FontManager.getCustomFont().FONT_HEIGHT / 2 + 1, // + 1 for better centering (for the eye)
                getTheme().getTextTransparent()
        );
    }
}
