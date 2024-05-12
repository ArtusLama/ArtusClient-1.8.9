package de.artus.artusmod.mods;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ModManager {

    @Getter(AccessLevel.PRIVATE)
    private Map<Mod, Boolean> mods = new HashMap<>();

    @Getter @Setter
    private ClientSettingsMod clientSettingsMod;


    public void registerMod(Mod mod, boolean defaultEnabled) {
        getMods().put(mod, defaultEnabled);
    }


    public void loadMods() {
        setClientSettingsMod(new ClientSettingsMod());
        getMods().put(getClientSettingsMod(), true);

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

    public List<Mod> getAllMods() {
        List<Mod> mods = new ArrayList<>();
        getMods().keySet().stream().filter(m -> !(m instanceof ClientSettingsMod)).forEach(mods::add);
        mods.sort(Comparator.comparing(Mod::getName));
        mods.add(0, getClientSettingsMod());
        return mods;
    }


}
