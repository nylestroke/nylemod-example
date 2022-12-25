package me.nylestroke.nylemod.command;

import com.mojang.brigadier.CommandDispatcher;
import me.nylestroke.nylemod.util.IEntityDataSaver;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;

public class HomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("sethome").executes((context -> {

            IEntityDataSaver player = (IEntityDataSaver) context.getSource().getPlayer();
            BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
            String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

            player.getPersistentData().putIntArray("homePos",
                    new int[]{ playerPos.getX(), playerPos.getY(), playerPos.getZ() });

            context.getSource().sendFeedback(new LiteralText("Set home at " + pos), true);
            return 1;

        })));

        dispatcher.register(CommandManager.literal("home").executes(context -> {
            IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();

            // not 0 means in contains SOMETHING
            int[] homePos = player.getPersistentData().getIntArray("homePos");

            if (homePos.length != 0) {
                int[] playerPos = player.getPersistentData().getIntArray("homePos");
                context.getSource().getPlayer().requestTeleport(playerPos[0], playerPos[1], playerPos[2]);

                context.getSource().sendFeedback(new LiteralText("Player returned Home!"), true);
                return 1;
            } else {
                context.getSource().sendFeedback(new LiteralText("No Home Position has been Set!"), true);
                return -1;
            }

        }));
    }

}
