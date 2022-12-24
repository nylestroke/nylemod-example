package me.nylestroke.nylemod;

import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.item.ModItems;
import me.nylestroke.nylemod.painting.ModPaintings;
import me.nylestroke.nylemod.util.ModRegistries;
import me.nylestroke.nylemod.world.feature.ModConfiguredFeatures;
import me.nylestroke.nylemod.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NylemodExample implements ModInitializer {
    public static final String MOD_ID = "nylemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModConfiguredFeatures.registerConfiguredFeatures();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModPaintings.registerPaintings();
        ModRegistries.registerModStuffs();

        ModWorldGen.generateModWorldGen();

    }
}
