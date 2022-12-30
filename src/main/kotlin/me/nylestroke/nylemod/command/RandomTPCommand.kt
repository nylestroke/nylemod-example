package me.nylestroke.nylemod.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.CommandManager.RegistrationEnvironment
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.World
import java.util.*
import kotlin.math.roundToInt

object RandomTPCommand {
    fun register(serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>,
                 commandRegistryAccess: CommandRegistryAccess,
                 registrationEnvironment: RegistrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("randomtp").executes(Command<ServerCommandSource> { context: CommandContext<ServerCommandSource> ->
            val source: ServerCommandSource = context.source
            val player: ServerPlayerEntity? = source.player
            val world: World = source.world
            val ranX = Random().nextDouble(player!!.x, player!!.x + 500)
            val ranY: Double = player!!.y
            val ranZ = Random().nextDouble(player.z, player.z + 500)
            player.requestTeleport(ranX, ranY, ranZ)
            source.sendFeedback(Text.literal("§e[RandomTP]§r Location Coordinates: §8(" +
                    ranX.roundToInt() + ", " + ranY.roundToInt() + ", " + ranZ.roundToInt() + ")§r"), false)
            1
        }))
    }
}