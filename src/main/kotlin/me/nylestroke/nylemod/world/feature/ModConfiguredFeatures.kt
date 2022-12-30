package me.nylestroke.nylemod.world.feature

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.ModBlocks
import net.minecraft.block.Blocks
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer

object ModConfiguredFeatures {
    val JACARANDA_TREE: RegistryEntry<ConfiguredFeature<TreeFeatureConfig, *>> = ConfiguredFeatures.register<TreeFeatureConfig, Feature<TreeFeatureConfig>>("jacaranda_tree", Feature.TREE, TreeFeatureConfig.Builder(
            BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
            StraightTrunkPlacer(5, 6, 3),
            BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
            BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
            TwoLayersFeatureSize(1, 0, 2)
    )
            .dirtProvider(BlockStateProvider.of(ModBlocks.MYTHRIL_BLOCK)).build())
    private val JACARANDA_CHECKED: RegistryEntry<PlacedFeature> = PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE,
            PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING))
    val JACARANDA_SPAWN: RegistryEntry<ConfiguredFeature<RandomFeatureConfig, *>> = ConfiguredFeatures.register<RandomFeatureConfig, Feature<RandomFeatureConfig>>("jacaranda_spawn", Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf<RandomFeatureEntry>(RandomFeatureEntry(JACARANDA_CHECKED, 0.5f)),
                    JACARANDA_CHECKED))
    val LILAC_FLOWER: RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, *>> = ConfiguredFeatures.register<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>("lilac_flower", Feature.FLOWER,
            ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LILAC_FLOWER)))))
    private val OVERWORLD_MYTHRIL_ORES: List<OreFeatureConfig.Target> = listOf<OreFeatureConfig.Target>(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.MYTHRIL_ORE.defaultState),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_MYTHRIL_ORE.defaultState),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK,
                    ModBlocks.NETHERRACK_MYTHRIL_ORE.defaultState))
    val MYTHRIL_ORE: RegistryEntry<ConfiguredFeature<OreFeatureConfig, *>> = ConfiguredFeatures.register<OreFeatureConfig, Feature<OreFeatureConfig>>("mythril_ore", Feature.ORE,
            OreFeatureConfig(OVERWORLD_MYTHRIL_ORES, 9))
    private val NETHER_MYTHRIL_ORES: List<OreFeatureConfig.Target> = listOf<OreFeatureConfig.Target>(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER,
                    ModBlocks.NETHERRACK_MYTHRIL_ORE.defaultState))
    private val END_MYTHRIL_ORES: List<OreFeatureConfig.Target> = listOf<OreFeatureConfig.Target>(
            OreFeatureConfig.createTarget(
                BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.END_MYTHRIL_ORE.defaultState))
    val NETHER_MYTHRIL_ORE: RegistryEntry<ConfiguredFeature<OreFeatureConfig, *>> = ConfiguredFeatures.register<OreFeatureConfig, Feature<OreFeatureConfig>>("nether_mythril_ore", Feature.ORE,
            OreFeatureConfig(NETHER_MYTHRIL_ORES, 12))
    val END_MYTHRIL_ORE: RegistryEntry<ConfiguredFeature<OreFeatureConfig, *>> = ConfiguredFeatures.register<OreFeatureConfig, Feature<OreFeatureConfig>>("end_mythril_ore", Feature.ORE,
            OreFeatureConfig(END_MYTHRIL_ORES, 12))

    fun registerConfiguredFeatures() {
        println("Registering ModConfiguredFeatures for " + NylemodExample.MOD_ID)
    }
}