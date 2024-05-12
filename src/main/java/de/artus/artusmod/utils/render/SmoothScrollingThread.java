package de.artus.artusmod.utils.render;

import de.artus.artusmod.ui.gui.lib.ScrollDetection;
import de.artus.artusmod.utils.ParametrizedFunction;
import lombok.Getter;
import lombok.Setter;

public class SmoothScrollingThread extends Thread {

    @Getter
    private final ScrollDetection scrollable;

    @Getter @Setter
    private double scrollAmount;


    @Getter
    private final ParametrizedFunction func = new ParametrizedFunction(2);

    @Getter
    private final double precisionSteps = 0.01;
    @Getter
    private final long scrollDuration = 200;

    public SmoothScrollingThread(ScrollDetection scrollable, double scrollAmount) {
        this.scrollable = scrollable;
        this.scrollAmount = scrollAmount;
    }


    @Override
    public void run() {
        getFunc().setA(0.5);
        for (double x = 0; x < 0.5; x += getPrecisionSteps()) {
            double speed = getFunc().of(x);
            getScrollable().applyScroll(speed * getScrollAmount() * x);
            waitFor((long) (getScrollDuration() * getPrecisionSteps() / 2));
        }

        getFunc().setA(4);
        for (double x = 0.5; x < 1; x += getPrecisionSteps() / 2) {
            double speed = getFunc().of(x);
            getScrollable().applyScroll(speed * getScrollAmount() * x);
            waitFor((long) (getScrollDuration() * getPrecisionSteps()));
        }

        getScrollable().setSmoothScrollingThread(null);
    }

    public void addScroll(double newScrollAmount) {
        setScrollAmount(getScrollAmount() + (newScrollAmount / 2));
    }


    private void waitFor(long mills) {
        try {
            sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
