package de.artus.artusmod.utils.keybindings;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.settings.KeyBinding;


@Getter
@Setter
public class ModKeybinding extends KeyBinding {

    private Runnable onPress = () -> {};
    private boolean triggerWhileKeyDown = false;

    public ModKeybinding(String name, int keyCode, String category) {
        super(name, keyCode, category);
    }
    public ModKeybinding(String name, int keyCode, String category, Runnable onPress, boolean triggerWhileKeyDown) {
        super(name, keyCode, category);
        setOnPress(onPress);
        setTriggerWhileKeyDown(triggerWhileKeyDown);
    }


}
