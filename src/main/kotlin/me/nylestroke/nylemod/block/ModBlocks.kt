package me.nylestroke.nylemod.block

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.custom.*
import me.nylestroke.nylemod.block.entity.ModSignTypes
import me.nylestroke.nylemod.fluid.ModFluids
import me.nylestroke.nylemod.sound.ModSounds
import me.nylestroke.nylemod.world.feature.tree.JacarandaSaplingGenerator
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import java.util.function.Supplier
import java.util.function.ToIntFunction

object ModBlocks {
    val MYTHRIL_BLOCK = registerBlock("mythril_block",
            Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),
            ItemGroup.MISC, "tooltip.nylemod.mythril_mod")
    val MYTHRIL_ORE = registerBlock("mythril_ore",
            OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool(),
                    UniformIntProvider.create(2, 6)), ItemGroup.MISC)
    val RAW_MYTHRIL_BLOCK = registerBlock("raw_mythril_block",
            Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ItemGroup.MISC)
    val NETHERRACK_MYTHRIL_ORE = registerBlock("netherrack_mythril_ore",
            OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool(),
                    UniformIntProvider.create(2, 6)), ItemGroup.MISC)
    val DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore",
            OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool(),
                    UniformIntProvider.create(2, 6)), ItemGroup.MISC)
    val SPEEDY_BLOCK = registerBlock("speedy_block",
            SpeedyBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_BUTTON = registerBlock("mythril_button",
            ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool().noCollision()), ItemGroup.MISC)
    val MYTHRIL_PRESSURE_PLATE = registerBlock("mythril_pressure_plate",
            ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_FENCE = registerBlock("mythril_fence",
            FenceBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_FENCE_GATE = registerBlock("mythril_fence_gate",
            FenceGateBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_WALL = registerBlock("mythril_wall",
            WallBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_SLAB = registerBlock("mythril_slab",
            SlabBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val MYTHRIL_STAIRS = registerBlock("mythril_stairs",
            ModStairsBlock(MYTHRIL_BLOCK.defaultState, FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()), ItemGroup.MISC)
    val VLADIK_DOOR = registerBlock("vladik_door",
            ModDoorBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(4.0f).requiresTool().nonOpaque()), ItemGroup.MISC)
    val VLADIK_TRAPDOOR = registerBlock("vladik_trapdoor",
            ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(4.0f).requiresTool().nonOpaque()), ItemGroup.MISC)
    val LILAC_FLOWER = registerBlock("lilac_flower",
            FlowerBlock(StatusEffects.FIRE_RESISTANCE, 12,
                    FabricBlockSettings.copy(Blocks.DANDELION).nonOpaque()), ItemGroup.MISC)
    val POTTED_LILAC_FLOWER = registerBlockWithoutBlockItem("potted_lilac_flower",
            FlowerPotBlock(LILAC_FLOWER, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM).nonOpaque()))
    val MYTHRIL_LAMP = registerBlock("mythril_lamp",
            MythrilLampBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool()
                    .luminance(ToIntFunction<BlockState> { state: BlockState ->
                        if (state.get(MythrilLampBlock.CLICKED)) 15 else 0 })
                    .sounds(ModSounds.MYTHRIL_SOUNDS)), ItemGroup.MISC)
    val WINTER_WINDOW = registerBlock("winter_window",
            GlassBlock(FabricBlockSettings.copy(Blocks.GLASS).strength(3.0f).nonOpaque()), ItemGroup.MISC)
    val GRAPE_VINE = registerBlockWithoutBlockItem("grape_vine",
            GrapeVineBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()))
    val JACARANDA_LOG = registerBlock("jacaranda_log",
            PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f)
                    .requiresTool()), ItemGroup.MISC)
    val JACARANDA_WOOD = registerBlock("jacaranda_wood",
            PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f)
                    .requiresTool()), ItemGroup.MISC)
    val STRIPPED_JACARANDA_LOG = registerBlock("stripped_jacaranda_log",
            PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f)
                    .requiresTool()), ItemGroup.MISC)
    val STRIPPED_JACARANDA_WOOD = registerBlock("stripped_jacaranda_wood",
            PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f)
                    .requiresTool()), ItemGroup.MISC)
    val JACARANDA_PLANKS = registerBlock("jacaranda_planks",
            Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f)
                    .requiresTool()), ItemGroup.MISC)
    val JACARANDA_LEAVES = registerBlock("jacaranda_leaves",
            LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()
                    .requiresTool()), ItemGroup.MISC)
    val JACARANDA_SAPLING = registerBlock("jacaranda_sapling",
            ModSaplingBlock(JacarandaSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING), Supplier { MYTHRIL_BLOCK }), ItemGroup.MISC)
    val MYTHRIL_BLASTER = registerBlock("mythril_blaster",
            MythrilBlasterBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ItemGroup.MISC)
    val JACARANDA_WALL_SIGN_BLOCK = registerBlockWithoutBlockItem("jacaranda_wall_sign",
            WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN), ModSignTypes.JACARANDA))
    val JACARANDA_SIGN_BLOCK = registerBlockWithoutBlockItem("jacaranda_sign",
            SignBlock(FabricBlockSettings.copy(Blocks.OAK_SIGN), ModSignTypes.JACARANDA))
    val HONEY_FLUID_BLOCK = registerBlockWithoutBlockItem("honey_fluid_block",
            ModFluidBlock(ModFluids.HONEY_STILL, FabricBlockSettings.of(Material.WATER)
                    .noCollision().nonOpaque().dropsNothing()))
    val END_MYTHRIL_ORE = registerBlock("end_mythril_ore",
            Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool()), ItemGroup.MISC)
    val GOLDEN_STAND = registerBlockWithoutBlockItem("golden_stand",
            GoldenStandBlock(FabricBlockSettings.of(Material.METAL).strength(6.0f).nonOpaque()))

    private fun registerBlock(name: String, block: Block, group: ItemGroup?, tooltipKey: String): Block {
        registerBlockItem(name, block, group, tooltipKey)
        return Registry.register(Registry.BLOCK, Identifier(NylemodExample.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block, group: ItemGroup?, tooltipKey: String): Item {
        return Registry.register(Registry.ITEM, Identifier(NylemodExample.MOD_ID, name),
                object : BlockItem(block, FabricItemSettings().group(group)) {
                    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>,
                                               context: TooltipContext) {
                        tooltip.add(Text.translatable(tooltipKey))
                    }
                })
    }

    private fun registerBlockWithoutBlockItem(name: String, block: Block): Block {
        return Registry.register(Registry.BLOCK, Identifier(NylemodExample.MOD_ID, name), block)
    }

    private fun registerBlock(name: String, block: Block, group: ItemGroup?): Block {
        registerBlockItem(name, block, group)
        return Registry.register(Registry.BLOCK, Identifier(NylemodExample.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block, group: ItemGroup?): Item {
        return Registry.register(Registry.ITEM, Identifier(NylemodExample.MOD_ID, name),
                BlockItem(block, FabricItemSettings().group(group)))
    }

    fun registerModBlocks() {
        NylemodExample.LOGGER.info("Registering ModBlocks for" + NylemodExample.MOD_ID)
    }
}