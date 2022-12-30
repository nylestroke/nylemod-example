package me.nylestroke.nylemod.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Lazy
import java.util.function.Supplier

enum class ModArmorMaterials(
    private val armorName: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    repairIngredientSupplier: Supplier<Ingredient>) : ArmorMaterial {

    MYTHRIL("mythril", 16, intArrayOf(2, 5, 7, 2), 28,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, Supplier { Ingredient.ofItems(ModItems.MYTHRIL_INGOT) });

    private val repairIngredientSupplier: Lazy<Ingredient>
    override fun getDurability(slot: EquipmentSlot): Int {
        return BASE_DURABILITY[slot.entitySlotId] * durabilityMultiplier
    }

    override fun getProtectionAmount(slot: EquipmentSlot): Int {
        return protectionAmounts[slot.entitySlotId]
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return equipSound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredientSupplier.get()
    }

    override fun getName(): String {
        return armorName
    }

    override fun getToughness(): Float {
        return toughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }

    init {
        this.repairIngredientSupplier = Lazy(repairIngredientSupplier)
    }

    companion object {
        private val BASE_DURABILITY: IntArray = intArrayOf(13, 15, 16, 11)

    }
}