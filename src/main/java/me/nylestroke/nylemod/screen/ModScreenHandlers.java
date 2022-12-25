package me.nylestroke.nylemod.screen;

import me.nylestroke.nylemod.NylemodExample;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<MythrilBlasterScreenHandler> MYTHRIL_BLASTER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        MYTHRIL_BLASTER_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(NylemodExample.MOD_ID, "mythril_blaster"),
                        MythrilBlasterScreenHandler::new);
    }

}
