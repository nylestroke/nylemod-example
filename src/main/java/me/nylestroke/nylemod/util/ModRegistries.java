package me.nylestroke.nylemod.util;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.command.ReturnHomeCommand;
import me.nylestroke.nylemod.command.SetHomeCommand;
import me.nylestroke.nylemod.event.ModPlayerEventCopyFrom;
import me.nylestroke.nylemod.item.ModItems;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {

    public static void registerModStuffs() {
        registerFuels();
        registerCommands();
        registerEvents();
    }

    private static void registerFuels() {
        NylemodExample.LOGGER.info("Registering Fuels for " + NylemodExample.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.LILAC_FLOWER_BULB, 200);
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }

    private static void registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }
}
