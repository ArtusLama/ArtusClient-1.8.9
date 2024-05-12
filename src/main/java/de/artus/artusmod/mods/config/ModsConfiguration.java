package de.artus.artusmod.mods.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import de.artus.artusmod.ArtusMod;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;


import java.io.*;

public class ModsConfiguration {

    public static final String MODS_CONFIG_FILE = "mods.json";
    public static final ResourceLocation MODS_CONFIG_RESOURCE = new ResourceLocation(ArtusMod.MODID + "/mods/" + MODS_CONFIG_FILE);


    public static ModsConfig getModsConfig() {
        Gson gson = new Gson();
        ModsConfig modsConfig;

        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(MODS_CONFIG_RESOURCE).getInputStream();

            JsonReader reader = new JsonReader(new InputStreamReader(is));
            modsConfig = gson.fromJson(reader, ModsConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            modsConfig = new ModsConfig();
            modsConfig.mods = new ModProps[0];
        }
        return modsConfig;
    }


    @Getter
    public static class ModProps {
        public String id;
        public String name;
        public String description;
    }

    @Getter
    public static class ModsConfig {

        public ModProps[] mods;

    }

}
