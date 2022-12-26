package me.nylestroke.nylemod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

import java.util.Random;

public class RandomTPCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("randomtp").executes((context -> {
                         ServerCommandSource source = context.getSource();
                         ServerPlayerEntity player = source.getPlayer();
                         World world = source.getWorld();

                         double ranX = new Random().nextDouble(player.getX(), player.getX() + 500);
                         double ranY = player.getY();
                         double ranZ = new Random().nextDouble(player.getZ(), player.getZ() + 500);

                        player.requestTeleport(ranX, ranY, ranZ);

                         source.sendFeedback(new LiteralText("§e[RandomTP]§r Location Coordinates: §8(" +
                                 Math.round(ranX) + ", " + Math.round(ranY) + ", " + Math.round(ranZ) + ")§r"), false);
                        return 1;

                })));
    }
}
