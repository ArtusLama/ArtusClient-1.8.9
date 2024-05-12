package de.artus.artusmod.ui.gui.lib.components.mods;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.gui.lib.containers.ListDirection;
import de.artus.artusmod.ui.gui.lib.containers.ScrollableListContainer;

import java.util.stream.Collectors;


public class ModMenuModsList extends ScrollableListContainer {

    public ModMenuModsList(int x, int y, int width, int height, int padding, int modItemSize) {
        super(x, y, width, height);
        setListDirection(ListDirection.VERTICAL);
        setPadding(padding);

        getItems().addAll(ArtusMod.getModManager().getAllMods().stream().map(
                mod -> new ModMenuMod(mod, modItemSize, modItemSize)
        ).collect(Collectors.toList()));

    }

}
