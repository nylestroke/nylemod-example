package me.nylestroke.nylemod.painting

import me.nylestroke.nylemod.NylemodExample
import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModPaintings {
    val MARATHON: PaintingVariant = registerPainting("marathon", PaintingVariant(16, 16))
    val FAMILY: PaintingVariant = registerPainting("family", PaintingVariant(16, 32))
    private fun registerPainting(name: String, paintingMotive: PaintingVariant): PaintingVariant {
        return Registry.register<PaintingVariant, PaintingVariant>(Registry.PAINTING_VARIANT, Identifier(NylemodExample.MOD_ID, name), paintingMotive)
    }

    fun registerPaintings() {
        NylemodExample.LOGGER.info("Registering Paintings for " + NylemodExample.MOD_ID)
    }
}