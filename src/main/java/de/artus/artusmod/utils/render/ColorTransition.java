package de.artus.artusmod.utils.render;

import lombok.*;
import lombok.experimental.Accessors;

@Getter @Setter
public class ColorTransition extends Color {

    private Color from;
    private Color to;


    private Color current;
    @Accessors(chain = true)
    private int duration = 150; // 150ms
    private long startTime;
    private int smoothness = 1;

    private Thread colorChangeThread;

    public ColorTransition(Color from, Color to) {
        super(from.getR(), from.getG(), from.getB(), from.getA());
        this.from = from;
        this.to = to;
    }


    public ColorTransition start() {
        setStartTime(System.currentTimeMillis());
        Thread colorChange = new Thread(() -> {
            while (System.currentTimeMillis() - getStartTime() < getDuration()) {
                double progress = (double) (System.currentTimeMillis() - getStartTime()) / getDuration();
                setCurrent(Color.of(
                        (int) (getFrom().getR() + (getTo().getR() - getFrom().getR()) * progress),
                        (int) (getFrom().getG() + (getTo().getG() - getFrom().getG()) * progress),
                        (int) (getFrom().getB() + (getTo().getB() - getFrom().getB()) * progress),
                        (int) (getFrom().getA() + (getTo().getA() - getFrom().getA()) * progress)
                ));
                this.r = getCurrent().getR();
                this.g = getCurrent().getG();
                this.b = getCurrent().getB();
                this.a = getCurrent().getA();

                try { Thread.sleep(getSmoothness()); }
                catch (InterruptedException e) { break; }
            }
            setCurrent(getTo());
            stop();
        });
        setColorChangeThread(colorChange);
        getColorChangeThread().start();

        return this;
    }
    public void stop() {
        if (getColorChangeThread() != null) {
            getColorChangeThread().interrupt();
            setColorChangeThread(null);
        }
    }


}
