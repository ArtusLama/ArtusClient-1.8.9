package de.artus.artusmod.mods;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.mods.config.ModsConfiguration;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public abstract class Mod {

    @Getter
    private String name = "No name set";
    @Getter
    private String description = "No description set";

    @Getter
    private ResourceLocation icon;

    public Mod(String id) {
        boolean found = false;
        for (ModsConfiguration.ModProps modProps : ModsConfiguration.getModsConfig().getMods()) {
            if (modProps.id.equals(id)) {
                this.name = modProps.getName();
                this.description = modProps.getDescription();
                this.icon = new ResourceLocation(ArtusMod.MODID, "mods/icons/" + id + ".png");
                found = true;
                break;
            }
        }
        if (!found) {
            ArtusMod.getLogger().warn("Mod with id " + id + " not found in " + ModsConfiguration.MODS_CONFIG_FILE + "!");
        }
    }


    public abstract void init();
    public abstract void onEnable();
    public abstract void onDisable();


    @Getter
    private boolean active = false;
    public void enableMod() {
        active = true;
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
    }
    public void disableMod() {
        active = false;
        MinecraftForge.EVENT_BUS.unregister(this);
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

    @Getter
    public boolean alwaysActive = false;


}
