package me.nylestroke.nylemod.world.feature

import net.minecraft.world.gen.placementmodifier.*

object ModOreFeatures {
    private fun modifiers(countModifier: PlacementModifier, heightModifier: PlacementModifier): List<PlacementModifier> {
        return listOf<PlacementModifier>(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())
    }

    fun modifiersWithCount(count: Int, heightModifier: PlacementModifier): List<PlacementModifier> {
        return modifiers(CountPlacementModifier.of(count), heightModifier)
    }

    fun modifiersWithRarity(chance: Int, heightModifier: PlacementModifier): List<PlacementModifier> {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier)
    }
}