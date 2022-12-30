package me.nylestroke.nylemod.recipe.custom

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.*
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.util.collection.DefaultedList
import net.minecraft.world.World

class MythrilBlasterRecipe(private val id: Identifier, private val output: ItemStack, private val recipeItems: DefaultedList<Ingredient>) : Recipe<SimpleInventory> {
    override fun matches(inventory: SimpleInventory, world: World): Boolean {
        if (world.isClient()) {
            return false
        }
        return if (recipeItems[0].test(inventory.getStack(1))) {
            recipeItems[1].test(inventory.getStack(2))
        } else false
    }

    override fun craft(inventory: SimpleInventory): ItemStack {
        return output
    }

    override fun fits(width: Int, height: Int): Boolean {
        return true
    }

    override fun getOutput(): ItemStack {
        return output.copy()
    }

    override fun getId(): Identifier {
        return id
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return Serializer.INSTANCE
    }

    override fun getType(): RecipeType<*> {
        return Type.INSTANCE
    }

    object Type : RecipeType<MythrilBlasterRecipe?> {
        val INSTANCE: Type = Type
        const val ID = "mythril_blaster"
    }

    class Serializer : RecipeSerializer<MythrilBlasterRecipe?> {
        // this is the name given in the json file
        override fun read(id: Identifier, json: JsonObject): MythrilBlasterRecipe {
            val output: ItemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"))
            val ingredients: JsonArray = JsonHelper.getArray(json, "ingredients")
            val inputs = DefaultedList.ofSize(2, Ingredient.EMPTY)
            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromJson(ingredients[i])
            }
            return MythrilBlasterRecipe(id, output, inputs)
        }

        override fun read(id: Identifier, buf: PacketByteBuf): MythrilBlasterRecipe {
            val inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)
            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromPacket(buf)
            }
            val output: ItemStack = buf.readItemStack()
            return MythrilBlasterRecipe(id, output, inputs)
        }

        override fun write(buf: PacketByteBuf, recipe: MythrilBlasterRecipe?) {
            if (recipe != null) {
                buf.writeInt(recipe.ingredients.size)
                for (ing in recipe.ingredients) {
                    ing.write(buf)
                }
                buf.writeItemStack(recipe.getOutput())
            }
        }

        companion object {
            val INSTANCE = Serializer()
            const val ID = "mythril_blaster"
        }
    }
}