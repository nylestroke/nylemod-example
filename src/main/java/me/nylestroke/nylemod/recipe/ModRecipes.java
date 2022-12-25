package me.nylestroke.nylemod.recipe;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.recipe.custom.MythrilBlasterRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(NylemodExample.MOD_ID, MythrilBlasterRecipe.Serializer.ID),
                MythrilBlasterRecipe.Serializer.INSTANCE);

        Registry.register(Registry.RECIPE_TYPE, new Identifier(NylemodExample.MOD_ID, MythrilBlasterRecipe.Type.ID),
                MythrilBlasterRecipe.Type.INSTANCE);
    }

}
