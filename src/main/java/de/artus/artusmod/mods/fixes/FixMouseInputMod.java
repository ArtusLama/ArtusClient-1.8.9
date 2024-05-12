package de.artus.artusmod.mods.fixes;

import com.google.common.util.concurrent.AtomicDouble;
import de.artus.artusmod.mods.Mod;
import lombok.Getter;
import lombok.Setter;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MouseHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FixMouseInputMod extends Mod {

    public FixMouseInputMod() {
        super("fixmouseinput");
    }


    @Getter
    private static final Set<Mouse> mice = new HashSet<>();

    @Getter @Setter
    private MouseHelper originalMouseHelper;
    @Getter @Setter
    private MouseHelper customMouseHelper;

    @Getter
    private final AtomicDouble dx = new AtomicDouble();
    @Getter
    private final AtomicDouble dy = new AtomicDouble();

    @Getter @Setter
    private Thread mouseInputThread;

    @Override
    public void init() {
        setOriginalMouseHelper(Minecraft.getMinecraft().mouseHelper);
        setCustomMouseHelper(new MouseHelper(){
            @Override
            public void mouseXYChange() {
                this.deltaX = (int) getDx().getAndSet(0);
                this.deltaY = (int) -getDy().getAndSet(0);
            }
        });
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().mouseHelper = getCustomMouseHelper();
        getMice().clear();
        getMice().addAll(this.getMice(ControllerEnvironment.getDefaultEnvironment()));

        setMouseInputThread(new Thread(() -> {
            while (true) {
                if (Minecraft.getMinecraft().currentScreen == null) {
                    mice.forEach(mouse -> {
                        mouse.poll();
                        getDx().addAndGet(mouse.getX().getPollData());
                        getDy().addAndGet(mouse.getY().getPollData());
                    });
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }));
        getMouseInputThread().setName("MouseInputThread[MOD]");
        getMouseInputThread().start();
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().mouseHelper = getOriginalMouseHelper();
        getMice().clear();
        getMouseInputThread().interrupt();
    }


    private Set<Mouse> getMice(ControllerEnvironment env) {
        return Arrays.stream(env.getControllers())
                .filter(Mouse.class::isInstance)
                .map(Mouse.class::cast)
                .collect(Collectors.toSet());
    }
}
