package me.nylestroke.nylemod.util;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.command.FreezeCommand;
import me.nylestroke.nylemod.command.ReturnHomeCommand;
import me.nylestroke.nylemod.command.SetHomeCommand;
import me.nylestroke.nylemod.event.ModPlayerEventCopyFrom;
import me.nylestroke.nylemod.item.ModItems;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModRegistries {

    public static void registerModStuffs() {
        registerFuels();
        registerCommands();
        registerEvents();
        registerFlammableBlock();
        registerStrippables();
        registerCustomTrades();
    }

    private static void registerFuels() {
        NylemodExample.LOGGER.info("Registering Fuels for " + NylemodExample.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.LILAC_FLOWER_BULB, 200);
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(FreezeCommand::freezeCommand);
        CommandRegistrationCallback.EVENT.register(FreezeCommand::unfreezeCommand);
    }

    private static void registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD);
    }

    private static void registerFlammableBlock() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20);

        instance.add(ModBlocks.JACARANDA_LEAVES, 30, 60);
    }

    private static void registerCustomTrades() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.GRAPE, 12),
                            6, 2, 0.02f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(ModItems.MYTHRIL_PICKAXE, 1),
                            12, 7, 0.08f));
                });

    }
}
