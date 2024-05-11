package de.artus.artusmod.mods;

import lombok.Getter;

public abstract class Mod {


    public Mod(String name) {
        this.name = name;
    }
    public Mod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Getter
    private final String name;
    @Getter
    private String description = "No description set";

    public abstract void init();
    public abstract void onEnable();
    public abstract void onDisable();


    @Getter
    private boolean active = false;
    public void enableMod() {
        active = true;
        onEnable();
    }
    public void disableMod() {
        active = false;
        onDisable();
    }
    public void toggleMod() {
        if (isActive()) disableMod();
        else enableMod();
    }
    public void setActive(boolean active) {
        if (active == isActive()) return;
        if (active) enableMod();
        else disableMod();
    }


}
