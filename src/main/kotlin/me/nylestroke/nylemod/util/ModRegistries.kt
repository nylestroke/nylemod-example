package me.nylestroke.nylemod.util

import com.mojang.brigadier.CommandDispatcher
import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.ModBlocks
import me.nylestroke.nylemod.command.FreezeCommand
import me.nylestroke.nylemod.command.HomeCommand
import me.nylestroke.nylemod.command.RandomTPCommand
import me.nylestroke.nylemod.entity.ModEntities
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import me.nylestroke.nylemod.event.ModPlayerEventCopyFrom
import me.nylestroke.nylemod.item.ModItems
import me.nylestroke.nylemod.villager.ModVillagers
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.command.CommandManager.RegistrationEnvironment
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.math.random.Random
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOffers
import net.minecraft.village.VillagerProfession
import java.util.function.Consumer

object ModRegistries {
    fun registerModStuffs() {
        registerFuels()
        registerCommands()
        registerEvents()
        registerFlammableBlock()
        registerStrippables()
        registerCustomTrades()
        registerAttributes()
        registerVillagers()
    }

    private fun registerFuels() {
        NylemodExample.LOGGER.info("Registering Fuels for " + NylemodExample.MOD_ID)
        val registry = FuelRegistry.INSTANCE
        registry.add(ModItems.LILAC_FLOWER_BULB, 200)
    }

    private fun registerCommands() {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>, commandRegistryAccess: CommandRegistryAccess, registrationEnvironment: RegistrationEnvironment -> HomeCommand.register(serverCommandSourceCommandDispatcher, commandRegistryAccess, registrationEnvironment) })
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>, commandRegistryAccess: CommandRegistryAccess, registrationEnvironment: RegistrationEnvironment -> FreezeCommand.register(serverCommandSourceCommandDispatcher, commandRegistryAccess, registrationEnvironment) })
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>, commandRegistryAccess: CommandRegistryAccess, registrationEnvironment: RegistrationEnvironment -> RandomTPCommand.register(serverCommandSourceCommandDispatcher, commandRegistryAccess, registrationEnvironment) })
    }

    private fun registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(ModPlayerEventCopyFrom())
    }

    private fun registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG)
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD)
    }

    private fun registerFlammableBlock() {
        val instance = FlammableBlockRegistry.getDefaultInstance()
        instance.add(ModBlocks.JACARANDA_LOG, 5, 5)
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5)
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5)
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5)
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20)
        instance.add(ModBlocks.JACARANDA_LEAVES, 30, 60)
    }

    private fun registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(
            VillagerProfession.FARMER, 1,
                Consumer<MutableList<TradeOffers.Factory?>> { factories: MutableList<TradeOffers.Factory?> ->
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 2),
                                ItemStack(ModItems.GRAPE, 12),
                                6, 2, 0.02f)
                    })
                })
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1,
                Consumer<MutableList<TradeOffers.Factory?>> { factories: MutableList<TradeOffers.Factory?> ->
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 6),
                                ItemStack(ModItems.MYTHRIL_PICKAXE, 1),
                                12, 7, 0.08f)
                    })
                })
    }

    private fun registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.RACCOON, RaccoonEntity.Companion.setAttributes())
    }

    private fun registerVillagers() {
        TradeOfferHelper.registerVillagerOffers(
            ModVillagers.BLAST_MASTER, 1,
                Consumer<MutableList<TradeOffers.Factory?>> { factories: MutableList<TradeOffers.Factory?> ->
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 6),
                                ItemStack(ModItems.MYTHRIL_PICKAXE, 1),
                                12, 7, 0.08f)
                    })
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 12),
                                ItemStack(ModItems.MYTHRIL_BOOTS, 1),
                                12, 7, 0.08f)
                    })
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 26),
                                ItemStack(ModItems.MYTHRIL_CHESTPLATE, 1),
                                12, 7, 0.08f)
                    })
                })
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 2,
                Consumer<MutableList<TradeOffers.Factory?>> { factories: MutableList<TradeOffers.Factory?> ->
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 64),
                                ItemStack(ModItems.MYTHRIL_HOE, 1),
                                12, 7, 0.08f)
                    })
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 5),
                                ItemStack(ModItems.MYTHRIL_INGOT, 1),
                                12, 7, 0.08f)
                    })
                    factories.add(TradeOffers.Factory { _: Entity?, _: Random? ->
                        TradeOffer(
                                ItemStack(Items.EMERALD, 50),
                                ItemStack(ModItems.VLADIKBOW, 1),
                                12, 7, 0.08f)
                    })
                })
    }
}