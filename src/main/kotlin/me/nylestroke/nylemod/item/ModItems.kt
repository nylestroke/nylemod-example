package me.nylestroke.nylemod.item

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.ModBlocks
import me.nylestroke.nylemod.entity.ModEntities
import me.nylestroke.nylemod.fluid.ModFluids
import me.nylestroke.nylemod.item.custom.*
import me.nylestroke.nylemod.sound.ModSounds
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {
    val MYTHRIL_INGOT = registerItem("mythril_ingot",
            Item(FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_NUGGET = registerItem("mythril_nugget",
            Item(FabricItemSettings().group(ItemGroup.MISC)))
    val RAW_MYTHRIL = registerItem("raw_mythril",
            Item(FabricItemSettings().group(ItemGroup.MISC)))
    val DOWSING_ROD = registerItem("dowsing_rod",
            DowsingRodItem(FabricItemSettings().group(ItemGroup.MISC).maxDamage(24)))
    val LILAC_FLOWER_BULB = registerItem("lilac_flower_bulb",
            Item(FabricItemSettings().group(ItemGroup.MISC)))
    val GRAPE = registerItem("grape",
            Item(FabricItemSettings().group(ItemGroup.MISC).food(ModFoodComponents.GRAPE)))
    val MYTHRIL_SWORD = registerItem("mythril_sword",
            ModSlownessSwordItem(ModToolMaterials.MYTHRIL, 2, 0f,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_AXE = registerItem("mythril_axe",
            ModAxeItem(ModToolMaterials.MYTHRIL, 3f, 1f,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_PICKAXE = registerItem("mythril_pickaxe",
            ModPickaxeItem(ModToolMaterials.MYTHRIL, 1, 0f,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_HOE = registerItem("mythril_hoe",
            ModHoeItem(ModToolMaterials.MYTHRIL, 0, 0f,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_SHOVEL = registerItem("mythril_shovel",
            ShovelItem(ModToolMaterials.MYTHRIL, 0f, 0f,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_HELMET = registerItem("mythril_helmet",
            MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.HEAD,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.CHEST,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.LEGS,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MYTHRIL_BOOTS = registerItem("mythril_boots",
            MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.FEET,
                    FabricItemSettings().group(ItemGroup.MISC)))
    val MAGIC_MYTHRIL_DUST = registerItem("magic_mythril_dust",
            Item(FabricItemSettings().group(ItemGroup.MISC)))
    val DATA_TABLET = registerItem("data_tablet",
            DataTabletItem(FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
    val GRAPE_SEEDS = registerItem("grape_seeds",
            AliasedBlockItem(ModBlocks.GRAPE_VINE, FabricItemSettings().group(ItemGroup.MISC)))
    val VLADIKBOW = registerItem("vladikbow",
            BowItem(FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(520)))
    val BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            ModMusicDiscItem(7, ModSounds.BAR_BRAWL, FabricItemSettings()
                    .group(ItemGroup.MISC).maxCount(1)))
    val HARD_BASS_MUSIC_DISC = registerItem("hard_bass_music_disc",
            ModMusicDiscItem(7, ModSounds.HARD_BASS, FabricItemSettings()
                    .group(ItemGroup.MISC).maxCount(1)))
    val MYTHRIL_STAFF = registerItem("mythril_staff",
            MythrilStaffItem(FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
    val JACARANDA_SIGN = registerItem("jacaranda_sign",
            SignItem(FabricItemSettings().group(ItemGroup.MISC).maxCount(16),
                    ModBlocks.JACARANDA_SIGN_BLOCK, ModBlocks.JACARANDA_WALL_SIGN_BLOCK))
    val HONEY_BUCKET = registerItem("honey_bucket",
            BucketItem(ModFluids.HONEY_STILL, FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
    val RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg",
            SpawnEggItem(ModEntities.RACCOON, 0x948e8d, 0x3b3635,
                    FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
    val LAGUNA_STAFF = registerItem("laguna_staff",
            LagunaStaffItem(FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
    val GOLDEN_STAND_ITEM = registerItem("golden_stand",
            GoldenStandItem(ModBlocks.GOLDEN_STAND,
                    FabricItemSettings().group(ItemGroup.MISC).maxCount(4)))

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(NylemodExample.MOD_ID, name), item)
    }

    fun registerModItems() {
        NylemodExample.LOGGER.info("Registered Mod Items for " + NylemodExample.MOD_ID)
    }
}