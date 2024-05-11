package de.artus.artusmod.ui.gui.lib.containers;

import de.artus.artusmod.ui.gui.lib.Clickable;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.Hoverable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ListContainer extends Drawable implements Clickable, Hoverable {


    @Getter @Setter
    private List<Drawable> items = new ArrayList<>();

    @Getter @Setter
    private int padding = 10;

    @Getter @Setter
    private AlignItems alignItems = AlignItems.START;
    @Getter @Setter
    private ListDirection listDirection = ListDirection.VERTICAL;

    public ListContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public ListContainer(int x, int y, int width, int height, List<Drawable> items) {
        super(x, y, width, height);
        setItems(items);
    }


    @Override
    public void draw(int mouseX, int mouseY) {
        //GL11.glEnable(GL11.GL_SCISSOR_TEST);
        //GL11.glScissor(getX(), getY(), getWidth(), getHeight());

        int startX = getX();
        int startY = getY();
        int xOffset = getListDirection() == ListDirection.REVERSE_HORIZONTAL ? getWidth() : 0;
        int yOffset = getListDirection() == ListDirection.REVERSE_VERTICAL ? getWidth() : 0;
        int xDir = getListDirection() == ListDirection.HORIZONTAL ? 1 : (getListDirection() == ListDirection.REVERSE_HORIZONTAL ? -1 : 0);
        int yDir = getListDirection() == ListDirection.VERTICAL ? 1 : (getListDirection() == ListDirection.REVERSE_VERTICAL ? -1 : 0);;


        for (Drawable item : getItems()) {
            item.setX(startX + xOffset + (getListDirection() == ListDirection.REVERSE_HORIZONTAL ? -item.getWidth() : 0));
            item.setY(startY + yOffset + (getListDirection() == ListDirection.REVERSE_VERTICAL ? -item.getWidth() : 0));
            item.draw(mouseX, mouseY);

            if (getListDirection() == ListDirection.HORIZONTAL || getListDirection() == ListDirection.REVERSE_HORIZONTAL) {
                startX += (item.getWidth() + getPadding()) * xDir;
            } else {
                startY += (item.getHeight() + getPadding()) * yDir;
            }
        }

        //GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }


    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {
        for (Drawable item : getItems()) {
            if (item instanceof Clickable) {
                Clickable clickable = (Clickable) item;
                if (clickable.isHovered(getX(), getY())) {
                    clickable.onClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }


    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight() &&
                getItems().stream().anyMatch(item -> item instanceof Hoverable && ((Hoverable) item).isHovered(mouseX, mouseY));
    }

    @Override
    public void onHovered(int mouseX, int mouseY) {
        for (Drawable item : getItems()) {
            if (item instanceof Hoverable) {
                Hoverable hoverable = (Hoverable) item;
                if (hoverable.isHovered(mouseX, mouseY)) {
                    hoverable.onHovered(mouseX, mouseY);
                } else hoverable.whileNotHovered();
            }
        }
    }

    @Override
    public void whileNotHovered() {
        for (Drawable item : getItems()) {
            if (item instanceof Hoverable) {
                Hoverable hoverable = (Hoverable) item;
                hoverable.whileNotHovered();
            }
        }
    }
}
