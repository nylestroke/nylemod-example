package me.nylestroke.nylemod.world.dimension;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.fluid.ModFluids;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<World> LAGUNA_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(NylemodExample.MOD_ID, "laguna"));

    public static final RegistryKey<DimensionType> LAGUNA_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            LAGUNA_DIMENSION_KEY.getValue());

    public static void registerDimensions() {
        NylemodExample.LOGGER.debug("Registering ModDimensions for: " + NylemodExample.MOD_ID);

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.MYTHRIL_BLOCK)
                .destDimID(LAGUNA_DIMENSION_KEY.getValue())
                .tintColor(240, 160, 60)
                .lightWithFluid(ModFluids.HONEY_STILL)
                .onlyLightInOverworld()
                .registerPortal();
    }

}
