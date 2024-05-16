package de.artus.artusmod.utils.mouse;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum MouseButton {

    LEFT(0),
    RIGHT(1),
    MIDDLE(2);

    @Getter
    private final int button;

    public static MouseButton of(int button) {
        return Arrays.stream(values()).filter(mb -> mb.getButton() == button).findFirst().orElse(null);
    }

}
