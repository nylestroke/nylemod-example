package me.nylestroke.nylemod.world.dimension

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.ModBlocks
import me.nylestroke.nylemod.fluid.ModFluids
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.dimension.DimensionType

object ModDimensions {
    val LAGUNA_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            Identifier(NylemodExample.MOD_ID, "laguna"))
    val LAGUNA_TYPE_KEY: RegistryKey<DimensionType> = RegistryKey.of<DimensionType>(Registry.DIMENSION_TYPE_KEY,
            LAGUNA_DIMENSION_KEY.value)

    fun registerDimensions() {
        NylemodExample.LOGGER.debug("Registering ModDimensions for: " + NylemodExample.MOD_ID)
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.MYTHRIL_BLOCK)
                .destDimID(LAGUNA_DIMENSION_KEY.value)
                .tintColor(240, 160, 60)
                .lightWithFluid(ModFluids.HONEY_STILL)
                .onlyLightInOverworld()
                .registerPortal()
    }
}