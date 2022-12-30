package me.nylestroke.nylemod.event

import me.nylestroke.nylemod.util.IEntityDataSaver
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.server.network.ServerPlayerEntity

class ModPlayerEventCopyFrom : ServerPlayerEvents.CopyFrom {
    override fun copyFromPlayer(oldPlayer: ServerPlayerEntity, newPlayer: ServerPlayerEntity, alive: Boolean) {
        val original: IEntityDataSaver = oldPlayer as IEntityDataSaver
        val player: IEntityDataSaver = newPlayer as IEntityDataSaver
        player.getPersistentData().putIntArray("homePos", original.getPersistentData().getIntArray("homePos"))
    }
}