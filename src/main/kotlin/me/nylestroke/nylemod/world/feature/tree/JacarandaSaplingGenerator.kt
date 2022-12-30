package me.nylestroke.nylemod.world.feature.tree

import me.nylestroke.nylemod.world.feature.ModConfiguredFeatures
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.math.random.Random
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.ConfiguredFeature

class JacarandaSaplingGenerator : SaplingGenerator() {
    override fun getTreeFeature(random: Random, bees: Boolean): RegistryEntry<out ConfiguredFeature<*, *>?> {
        return ModConfiguredFeatures.JACARANDA_TREE
    }
}