package me.nylestroke.nylemod.painting;

import me.nylestroke.nylemod.NylemodExample;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {

    public static final PaintingMotive MARATHON = registerPainting("marathon", new PaintingMotive(16, 16));
    public static final PaintingMotive FAMILY = registerPainting("family", new PaintingMotive(16, 32));

    private static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(NylemodExample.MOD_ID, name), paintingMotive);
    }


    public static void registerPaintings() {
        NylemodExample.LOGGER.info("Registering Paintings for " + NylemodExample.MOD_ID);
    }

}
