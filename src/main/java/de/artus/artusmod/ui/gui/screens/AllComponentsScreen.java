package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ui.gui.lib.components.interactable.BasicButton;
import de.artus.artusmod.ui.gui.lib.components.shapes.CircleShape;
import de.artus.artusmod.ui.gui.lib.components.shapes.RectShape;
import de.artus.artusmod.ui.gui.lib.components.shapes.RoundedRectShape;

public class AllComponentsScreen extends AScreen {


    @Override
    public void init() {
        getElements().add(
                new RectShape(10, 10, 50, 30)
                        .setBackgroundColor(getGuiConfiguration().getTheme().getPrimary())
        );
        getElements().add(
                new RoundedRectShape(70, 10, 50, 30, 8)
                        .setBackgroundColor(getGuiConfiguration().getTheme().getPrimary())
        );
        getElements().add(
                new CircleShape(130, 10, 15)
                        .setBackgroundColor(getGuiConfiguration().getTheme().getPrimary())
        );

        getElements().add(
                new BasicButton(10, 60, 50, 30)
        );
    }


}
