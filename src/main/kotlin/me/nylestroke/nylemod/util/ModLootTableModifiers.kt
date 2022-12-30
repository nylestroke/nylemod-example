package me.nylestroke.nylemod.util

import me.nylestroke.nylemod.item.ModItems
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.minecraft.loot.LootManager
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier

object ModLootTableModifiers {
    private val GRASS_BLOCK_ID = Identifier("minecraft", "blocks/grass")
    private val IGLOO_STRUCTURE_CHEST_ID = Identifier("minecraft", "chests/igloo_chest")
    private val CREEPER_ID = Identifier("minecraft", "entities/creeper")
    fun modifyLootTables() {
        LootTableEvents.MODIFY.register(LootTableEvents.Modify { _: ResourceManager?, _: LootManager?, id: Identifier, supplier: LootTable.Builder, _: LootTableSource? ->
            //check for grass block loot table.
            if (GRASS_BLOCK_ID == id) {
                // Adds Grape Seeds to the grass loot table.
                val poolBuilder: LootPool.Builder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
                    .with(ItemEntry.builder(ModItems.GRAPE_SEEDS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build())
                supplier.pool(poolBuilder.build())
            }
            if (IGLOO_STRUCTURE_CHEST_ID == id) {
                // Adds a Dowsing Rod into the Igloo Chest with 100% chance.
                val poolBuilder: LootPool.Builder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(0.75f)) // Drops 75% of the time
                    .with(ItemEntry.builder(ModItems.DOWSING_ROD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                supplier.pool(poolBuilder.build())
            }
            if (CREEPER_ID == id) {
                // Adds a Lilac Flower Bulb to Creepers.
                val poolBuilder: LootPool.Builder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                    .with(ItemEntry.builder(ModItems.LILAC_FLOWER_BULB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                supplier.pool(poolBuilder.build())
            }
        })
    }
}