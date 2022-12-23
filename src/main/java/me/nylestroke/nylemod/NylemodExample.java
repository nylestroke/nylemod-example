package me.nylestroke.nylemod;

import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NylemodExample implements ModInitializer {
    public static final String MOD_ID = "nylemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

    }
}
