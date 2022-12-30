package me.nylestroke.nylemod.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import me.nylestroke.nylemod.effect.ModEffects
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.command.EntitySelector
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.CommandManager.RegistrationEnvironment
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object FreezeCommand {
    fun register(serverCommandSourceCommandDispatcher: CommandDispatcher<ServerCommandSource>,
                 commandRegistryAccess: CommandRegistryAccess,
                 registrationEnvironment: RegistrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("freeze")
                .then(CommandManager.argument<EntitySelector>("targets", EntityArgumentType.entities()).executes(Command<ServerCommandSource> { context: CommandContext<ServerCommandSource> -> execute(context.source, EntityArgumentType.getEntities(context, "targets"), false) })))
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("unfreeze")
                .then(CommandManager.argument<EntitySelector>("targets", EntityArgumentType.entities()).executes(Command<ServerCommandSource> { context: CommandContext<ServerCommandSource> -> execute(context.source, EntityArgumentType.getEntities(context, "targets"), true) })))
    }

    @Throws(CommandSyntaxException::class)
    fun execute(source: ServerCommandSource, targets: Collection<Entity>, frozen: Boolean): Int {
        val var3 = targets.iterator()
        var i = 0
        while (var3.hasNext()) {
            val entity = var3.next()
            if (entity is LivingEntity) {
                if (frozen) {
                    entity.removeStatusEffect(ModEffects.FREEZE)
                } else {
                    entity.addStatusEffect(StatusEffectInstance(ModEffects.FREEZE, 999999999))
                }
                ++i
            }
        }
        return if (i == 0) {
            throw SimpleCommandExceptionType(Text.literal("§b[Freeze]§r Unable to find user, cannot execute a command")).create()
        } else {
            if (i == 1) {
                source.sendFeedback(Text.literal("§b[" + (if (frozen) "UnFreeze" else "Freeze") + "]§r Player §7" +
                        targets.iterator().next().entityName + "§r successfully " + if (frozen) "unfrozen" else "frozen"), true)
            } else {
                source.sendFeedback(Text.literal("§b[Freeze]§r Count of involved players: §7" +
                        targets.size + "§r"), true)
            }
            i
        }
    }
}