package de.artus.artusmod.utils.keybindings;

import de.artus.artusmod.ArtusMod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeybindHelper {

    @AllArgsConstructor
    public enum Keys {

        OPEN_MOD_MENU("Open Mod Menu", Keyboard.KEY_RSHIFT);

        private final String name;
        private final int keyCode;
    }

    public static class KeyInputListener {

        @SubscribeEvent
        public void onKeyInput(InputEvent.KeyInputEvent event) {
            for (ModKeybinding key : getKeyBindings()) {
                if ((key.isPressed() && !key.isTriggerWhileKeyDown()) || (key.isKeyDown() && key.isTriggerWhileKeyDown())) {
                    Runnable action = key.getOnPress();
                    if (action != null) {
                        action.run();
                    }
                }
            }
        }

    }

    @Getter
    private static List<ModKeybinding> keyBindings = new ArrayList<>();

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new KeyInputListener());
    }

    public static void registerKey(Keys key) { registerKey(key, () -> {}); }
    public static void registerKey(Keys key, Runnable onPress) {
        registerKey(key, onPress, false);
    }
    public static void registerKey(Keys key, Runnable onPress, boolean triggerWhileKeyDown) {
        ModKeybinding keyBinding = new ModKeybinding(key.name, key.keyCode, ArtusMod.MODID, onPress, triggerWhileKeyDown);
        getKeyBindings().add(keyBinding);
        ClientRegistry.registerKeyBinding(keyBinding);
    }





}
