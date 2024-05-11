package de.artus.artusmod.mods;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;

public class ModManager {

    @Getter(AccessLevel.PRIVATE)
    private Map<Mod, Boolean> mods = new HashMap<>();


    public void registerMod(Mod mod, boolean defaultEnabled) {
        getMods().put(mod, defaultEnabled);
    }


    public void loadMods() {
        for (Map.Entry<Mod, Boolean> mod : getMods().entrySet()) {
            mod.getKey().init();
            if (mod.getValue()) {
                mod.getKey().enableMod();
            }
        }
    }

    public boolean isModEnabled(Class<? extends Mod> modClass) {
        for (Mod mod : getMods().keySet()) {
            if (mod.getClass().equals(modClass)) {
                return mod.isActive();
            }
        }
        return false;
    }


}
