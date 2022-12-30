package me.nylestroke.nylemod

import me.nylestroke.nylemod.block.ModBlocks
import me.nylestroke.nylemod.block.entity.ModBlockEntities
import me.nylestroke.nylemod.effect.ModEffects
import me.nylestroke.nylemod.enchantment.ModEnchantments
import me.nylestroke.nylemod.item.ModItems
import me.nylestroke.nylemod.painting.ModPaintings
import me.nylestroke.nylemod.particle.ModParticles
import me.nylestroke.nylemod.potion.ModPotions
import me.nylestroke.nylemod.recipe.ModRecipes
import me.nylestroke.nylemod.screen.ModScreenHandlers
import me.nylestroke.nylemod.util.ModLootTableModifiers
import me.nylestroke.nylemod.util.ModRegistries
import me.nylestroke.nylemod.world.dimension.ModDimensions
import me.nylestroke.nylemod.world.feature.ModConfiguredFeatures
import me.nylestroke.nylemod.world.gen.ModWorldGen
import me.nylestroke.nylemod.world.structure.ModStructures
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.bernie.geckolib3.GeckoLib

class NylemodExample : ModInitializer {
    override fun onInitialize() {
        ModConfiguredFeatures.registerConfiguredFeatures()
        ModItems.registerModItems()
        ModBlocks.registerModBlocks()
        ModPaintings.registerPaintings()
        ModRegistries.registerModStuffs()
        ModWorldGen.generateModWorldGen()
        ModLootTableModifiers.modifyLootTables()
        ModEffects.registerEffects()
        ModPotions.registerPotions()
        ModBlockEntities.registerAllBlockEntities()
        ModRecipes.registerRecipes()
        ModScreenHandlers.registerAllScreenHandlers()
        ModParticles.registerParticles()
        ModEnchantments.registerModEnchantments()
        GeckoLib.initialize()
        ModStructures.registerStructureFeatures()
        ModDimensions.registerDimensions()
    }

    companion object {
        const val MOD_ID = "nylemod"
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}