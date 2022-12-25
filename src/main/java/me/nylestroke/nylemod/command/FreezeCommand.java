package me.nylestroke.nylemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import me.nylestroke.nylemod.effect.ModEffects;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.util.Collection;
import java.util.Iterator;

public class FreezeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("freeze")
                .then(CommandManager.argument("targets", EntityArgumentType.entities()).executes((context) ->
                        execute(context.getSource(), EntityArgumentType.getEntities(context, "targets"), false))));

        dispatcher.register(CommandManager.literal("unfreeze")
                .then(CommandManager.argument("targets", EntityArgumentType.entities()).executes((context) ->
                        execute(context.getSource(), EntityArgumentType.getEntities(context, "targets"), true))));
    }

    public static int execute(ServerCommandSource source, Collection<? extends Entity> targets, boolean frozen) throws CommandSyntaxException {
        Iterator<? extends Entity> var3 = targets.iterator();
        int i = 0;

        while(var3.hasNext()) {

            Entity entity = (Entity)var3.next();

            if (entity instanceof LivingEntity) {
                if (frozen) {
                    ((LivingEntity) entity).removeStatusEffect(ModEffects.FREEZE);
                } else {
                    ((LivingEntity)entity).addStatusEffect((new StatusEffectInstance(ModEffects.FREEZE, 999999999)));
                }

                ++i;
            }
        }

        if (i == 0) {

            throw new SimpleCommandExceptionType(new LiteralText("§b[Freeze]§r Unable to find user, cannot execute a command")).create();

        } else {

            if (i == 1) {
                source.sendFeedback(new LiteralText("§b[" + (frozen ? "UnFreeze" : "Freeze") + "]§r Player §7" +
                        ((Entity) targets.iterator().next()).getEntityName() + "§r successfully " + (frozen ? "unfrozen" : "frozen")), true);
            } else {
                source.sendFeedback(new LiteralText("§b[Freeze]§r Count of involved players: §7" +
                        targets.size() + "§r"), true);
            }


            return i;

        }
    }

}
