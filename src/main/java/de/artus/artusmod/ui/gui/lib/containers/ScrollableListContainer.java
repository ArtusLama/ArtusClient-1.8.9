package de.artus.artusmod.ui.gui.lib.containers;

import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.ScrollDetection;
import de.artus.artusmod.utils.render.SmoothScrollingThread;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ScrollableListContainer extends ListContainer implements ScrollDetection {

    @Getter @Setter
    private double scrollPosition = 0;

    @Getter @Setter
    private boolean smoothScroll = true;
    @Getter @Setter
    private SmoothScrollingThread smoothScrollingThread = null;

    @Getter @Setter
    private double scrollSpeed = 0.005;

    public ScrollableListContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public ScrollableListContainer(int x, int y, int width, int height, List<Drawable> items) {
        super(x, y, width, height, items);
    }


    @Override
    public void onScroll(int scrollAmount) {
        if (scrollAmount == 0) return;
        double scroll = scrollAmount * getScrollSpeed();

        if (getSmoothScrollingThread() != null) {
            getSmoothScrollingThread().addScroll(scroll);
            return;
        }

        setSmoothScrollingThread(new SmoothScrollingThread(this, scroll));
        getSmoothScrollingThread().start();
    }

    @Override
    public void applyScroll(double scrollAmount) {
        if (getHeight() - getContentHeight() > 0) {
            setScrollPosition(0);
            return;
        }

        double newPos = getScrollPosition() + scrollAmount;
        if (getHeight() - newPos > getContentHeight()) {
            setScrollPosition(getHeight() - getContentHeight());
            return;
        }
        if (newPos > 0) {
            setScrollPosition(0);
            return;
        }


        setScrollPosition(getScrollPosition() + scrollAmount);
        if (getListDirection() == ListDirection.VERTICAL || getListDirection() == ListDirection.REVERSE_VERTICAL) {
            setBaseYOffset((int) getScrollPosition());
        }
        if (getListDirection() == ListDirection.HORIZONTAL || getListDirection() == ListDirection.REVERSE_HORIZONTAL) {
            setBaseXOffset((int) getScrollPosition());
        }
    }

    public int getContentHeight() {
        return getItems().stream().mapToInt(d -> d.getHeight() + getPadding()).sum() - getPadding();
    }


    @Override
    public boolean isInScrollArea(int mouseX, int mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight();
    }
}
