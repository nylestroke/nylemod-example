package me.nylestroke.nylemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.nylestroke.nylemod.util.IEntityDataSaver;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class ReturnHomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("return").executes(ReturnHomeCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
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
    }

}
