package de.artus.artusmod.ui.gui.OLD.lib.components;

import de.artus.artusmod.ui.gui.OLD.lib.Clickable;
import de.artus.artusmod.ui.gui.OLD.lib.interactive.RoundedButton;
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
        setCurrentBackgroundColor(ColorUtil.setAlpha(getTheme().getDangerColorOLD(), 150));
    }
}
