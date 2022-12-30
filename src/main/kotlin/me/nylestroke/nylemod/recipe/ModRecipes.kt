package me.nylestroke.nylemod.recipe

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.recipe.custom.MythrilBlasterRecipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModRecipes {
    fun registerRecipes() {
        Registry.register<RecipeSerializer<*>, MythrilBlasterRecipe.Serializer>(Registry.RECIPE_SERIALIZER, Identifier(NylemodExample.MOD_ID, MythrilBlasterRecipe.Serializer.Companion.ID),
                MythrilBlasterRecipe.Serializer.Companion.INSTANCE)
        Registry.register<RecipeType<*>, MythrilBlasterRecipe.Type>(Registry.RECIPE_TYPE, Identifier(NylemodExample.MOD_ID, MythrilBlasterRecipe.Type.ID),
                MythrilBlasterRecipe.Type.INSTANCE)
    }
}