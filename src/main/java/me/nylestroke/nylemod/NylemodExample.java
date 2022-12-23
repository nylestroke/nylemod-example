package me.nylestroke.nylemod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NylemodExample implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("nylemod");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
    }
}
