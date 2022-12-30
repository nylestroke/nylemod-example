package me.nylestroke.nylemod.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import me.nylestroke.nylemod.util.IEntityDataSaver
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.CommandManager.RegistrationEnvironment
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

object HomeCommand {
    fun register(serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>,
                 commandRegistryAccess: CommandRegistryAccess,
                 registrationEnvironment: RegistrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("sethome").executes(Command<ServerCommandSource?>
        { context: CommandContext<ServerCommandSource?> ->
            val player: IEntityDataSaver = context.source?.player as IEntityDataSaver
            val playerPos: BlockPos? = context.source!!.player?.blockPos
            val pos = "(" + playerPos!!.x + ", " + playerPos!!.y + ", " + playerPos.z + ")"
            player.getPersistentData().putIntArray("homePos", intArrayOf(playerPos.x, playerPos.y, playerPos.z))
            context.source?.sendFeedback(Text.literal("Set home at $pos"), true)
            1
        }))

        serverCommandSourceCommandDispatcher.register(CommandManager.literal("home").executes(Command<ServerCommandSource?>
        executes@{ context: CommandContext<ServerCommandSource?> ->
            val player: IEntityDataSaver = context.source?.player as IEntityDataSaver

            // not 0 means in contains SOMETHING
            val homePos: IntArray = player.getPersistentData().getIntArray("homePos")
            if (homePos.isNotEmpty()) {
                val playerPos: IntArray = player.getPersistentData().getIntArray("homePos")
                context.source!!.player?.requestTeleport(playerPos[0].toDouble(), playerPos[1].toDouble(), playerPos[2].toDouble())
                context.source!!.sendFeedback(Text.literal("Player returned Home!"), true)
                return@executes 1
            } else {
                context.source!!.sendFeedback(Text.literal("No Home Position has been Set!"), true)
                return@executes -1
            }
        }))
    }
}