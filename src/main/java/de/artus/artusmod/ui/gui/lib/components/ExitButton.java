package de.artus.artusmod.ui.gui.lib.components;

import de.artus.artusmod.ui.gui.lib.Clickable;
import de.artus.artusmod.ui.gui.lib.interactive.RoundedButton;
import de.artus.artusmod.utils.render.ColorUtil;

public class ExitButton extends RoundedButton implements Clickable {


    public ExitButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
        setCornerRadius(3);
    }
    public ExitButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        setCornerRadius(3);
    }


    @Override
    public void onHovered(int mouseX, int mouseY) {
        setCurrentBackgroundColor(ColorUtil.setAlpha(getTheme().getDangerColor(), 150));
    }
}
