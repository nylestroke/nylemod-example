package me.nylestroke.nylemod.item;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.fluid.ModFluids;
import me.nylestroke.nylemod.item.custom.*;
import me.nylestroke.nylemod.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item RAW_MYTHRIL = registerItem("raw_mythril",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxDamage(24)));

    public static final Item LILAC_FLOWER_BULB = registerItem("lilac_flower_bulb",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD).food(ModFoodComponents.GRAPE)));

    public static final Item MYTHRIL_SWORD = registerItem("mythril_sword",
            new ModSlownessSwordItem(ModToolMaterials.MYTHRIL, 2, 0f,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_AXE = registerItem("mythril_axe",
            new ModAxeItem(ModToolMaterials.MYTHRIL, 3, 1f,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_PICKAXE = registerItem("mythril_pickaxe",
            new ModPickaxeItem(ModToolMaterials.MYTHRIL, 1, 0f,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_HOE = registerItem("mythril_hoe",
            new ModHoeItem(ModToolMaterials.MYTHRIL, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_SHOVEL = registerItem("mythril_shovel",
            new ShovelItem(ModToolMaterials.MYTHRIL, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_HELMET = registerItem("mythril_helmet",
            new ModArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MYTHRIL_BOOTS = registerItem("mythril_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item MAGIC_MYTHRIL_DUST = registerItem("magic_mythril_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item DATA_TABLET = registerItem("data_tablet",
            new DataTabletItem(new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxCount(1)));

    public static final Item GRAPE_SEEDS = registerItem("grape_seeds",
            new AliasedBlockItem(ModBlocks.GRAPE_VINE, new FabricItemSettings().group(ModItemGroup.NYLEMOD)));

    public static final Item VLADIKBOW = registerItem("vladikbow",
            new BowItem(new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxCount(1).maxDamage(520)));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new ModMusicDiscItem(7, ModSounds.BAR_BRAWL,new FabricItemSettings()
                    .group(ModItemGroup.NYLEMOD).maxCount(1)));

    public static final Item HARD_BASS_MUSIC_DISC = registerItem("hard_bass_music_disc",
            new ModMusicDiscItem(7, ModSounds.HARD_BASS,new FabricItemSettings()
                    .group(ModItemGroup.NYLEMOD).maxCount(1)));

    public static final Item MYTHRIL_STAFF = registerItem("mythril_staff",
            new MythrilStaffItem(new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxCount(1)));

    public static final Item JACARANDA_SIGN = registerItem("jacaranda_sign",
            new SignItem(new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxCount(16),
                    ModBlocks.JACARANDA_SIGN_BLOCK, ModBlocks.JACARANDA_WALL_SIGN_BLOCK));

    public static final Item HONEY_BUCKET = registerItem("honey_bucket",
            new BucketItem(ModFluids.HONEY_STILL, new FabricItemSettings().group(ModItemGroup.NYLEMOD).maxCount(1)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(NylemodExample.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NylemodExample.LOGGER.info("Registered Mod Items for " + NylemodExample.MOD_ID);
    }
}
