package me.nylestroke.nylemod.world.feature;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            ConfiguredFeatures.register("jacaranda_tree",  Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .dirtProvider(BlockStateProvider.of(ModBlocks.MYTHRIL_BLOCK)).build());


    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5f)),
                            JACARANDA_CHECKED));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LILAC_FLOWER =
            ConfiguredFeatures.register("lilac_flower", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LILAC_FLOWER)))));

    public static final List<OreFeatureConfig.Target> OVERWORLD_MYTHRIL_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.MYTHRIL_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_MYTHRIL_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK,
                    ModBlocks.NETHERRACK_MYTHRIL_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> MYTHRIL_ORE =
            ConfiguredFeatures.register("mythril_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_MYTHRIL_ORES, 9));

    public static final List<OreFeatureConfig.Target> NETHER_MYTHRIL_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER,
                    ModBlocks.NETHERRACK_MYTHRIL_ORE.getDefaultState()));

    public static final List<OreFeatureConfig.Target> END_MYTHRIL_ORES = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.END_MYTHRIL_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NETHER_MYTHRIL_ORE =
            ConfiguredFeatures.register("nether_mythril_ore",Feature.ORE,
                    new OreFeatureConfig(NETHER_MYTHRIL_ORES, 12));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_MYTHRIL_ORE =
            ConfiguredFeatures.register("end_mythril_ore",Feature.ORE,
                    new OreFeatureConfig(END_MYTHRIL_ORES, 12));

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + NylemodExample.MOD_ID);
    }

}
