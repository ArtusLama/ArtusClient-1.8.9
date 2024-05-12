package de.artus.artusmod.ui.gui.lib;

import de.artus.artusmod.utils.render.SmoothScrollingThread;

public interface ScrollDetection {

    void onScroll(int scrollAmount);
    void applyScroll(double scrollAmount);

    void setSmoothScrollingThread(SmoothScrollingThread smoothScrollingThread);
    SmoothScrollingThread getSmoothScrollingThread();

    boolean isInScrollArea(int mouseX, int mouseY);

}
