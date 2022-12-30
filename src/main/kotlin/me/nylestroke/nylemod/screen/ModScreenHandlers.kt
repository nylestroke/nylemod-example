package me.nylestroke.nylemod.screen

import me.nylestroke.nylemod.NylemodExample
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier

object ModScreenHandlers {
    var MYTHRIL_BLASTER_SCREEN_HANDLER: ScreenHandlerType<MythrilBlasterScreenHandler>? = null
    fun registerAllScreenHandlers() {
        MYTHRIL_BLASTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple<MythrilBlasterScreenHandler>(Identifier(NylemodExample.MOD_ID, "mythril_blaster"),
            ScreenHandlerRegistry.SimpleClientHandlerFactory<MythrilBlasterScreenHandler> { syncId: Int, playerInventory: PlayerInventory ->
                MythrilBlasterScreenHandler(
                    syncId,
                    playerInventory
                )
            })
    }
}