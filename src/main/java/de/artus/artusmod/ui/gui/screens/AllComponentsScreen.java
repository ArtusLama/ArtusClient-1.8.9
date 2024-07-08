package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ui.gui.lib.components.interactable.BasicButton;
import de.artus.artusmod.ui.gui.lib.components.shapes.CircleShape;
import de.artus.artusmod.ui.gui.lib.components.shapes.RectShape;
import de.artus.artusmod.ui.gui.lib.components.shapes.RoundedRectShape;
import de.artus.artusmod.ui.gui.lib.components.text.CenteredTextContainer;
import de.artus.artusmod.ui.gui.lib.components.text.TextContainer;
import de.artus.artusmod.ui.gui.lib.font.CustomFont;
import de.artus.artusmod.ui.gui.lib.font.FontManager;
import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.utils.render.Color;

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
                new BasicButton(190, 10, 50, 30)
        );

        getElements().add(new TextContainer(10, 50, 100, 20, "Hello World!"));
        getElements().add(new TextContainer(120, 50, 100, 50, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nunc nec ultricies.")
                .setClipToWidth(true));
        getElements().add(new CenteredTextContainer(250, 50, 100, 75, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nunc nec ultricies.")
                .setClipToWidth(true).setLinePadding(5));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, particalTicks);

        DrawHelper.drawRectOutline(10, 10, 50, 30, 2, Color.of(255, 0, 0, 200));

        DrawHelper.drawCircleOutline(130, 10, 15, 5, Color.of(0, 255, 0));

        DrawHelper.drawFragmentCircleOutline(70, 10, 270, 360, 8, 2, Color.of(0, 255, 0));

        DrawHelper.drawRoundedRectOutline(190, 10, 50, 30, 4, 4, Color.of(50, 50, 200, 200));


        CustomFont customFont = (CustomFont) FontManager.getCustomFont();
        customFont.getRenderer().drawString("Hello World!", 10, 100, Color.of(255, 0, 255).getInt());

    }
}
