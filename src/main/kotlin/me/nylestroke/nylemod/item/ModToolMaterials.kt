package me.nylestroke.nylemod.item

import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Lazy
import java.util.function.Supplier

enum class ModToolMaterials(private val miningLevel: Int, private val itemDurability: Int, private val miningSpeed: Float, private val attackDamage: Float, private val enchantability: Int, repairIngredient: Supplier<Ingredient>) : ToolMaterial {
    MYTHRIL(MiningLevels.IRON, 470, 10.0f, 2.5f, 24, Supplier { Ingredient.ofItems(ModItems.MYTHRIL_INGOT) });

    private val repairIngredient: Lazy<Ingredient>

    init {
        this.repairIngredient = Lazy(repairIngredient)
    }

    override fun getDurability(): Int {
        return itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return miningSpeed
    }

    override fun getAttackDamage(): Float {
        return attackDamage
    }

    override fun getMiningLevel(): Int {
        return miningLevel
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}