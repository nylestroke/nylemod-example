package me.nylestroke.nylemod.item.custom

import com.google.common.collect.ImmutableMap
import me.nylestroke.nylemod.item.ModArmorMaterials
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World

open class ModArmorItem(material: ArmorMaterial?, slot: EquipmentSlot?, settings: Settings?) : ArmorItem(material, slot, settings) {
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient()) {
            if (entity is PlayerEntity) {
                val player = entity
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player)
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    private fun evaluateArmorEffects(player: PlayerEntity) {
        for ((mapArmorMaterial, mapStatusEffect) in MATERIAL_TO_EFFECT_MAP) {
            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect)
            }
        }
    }

    private fun addStatusEffectForMaterial(player: PlayerEntity, mapArmorMaterial: ArmorMaterial, mapStatusEffect: StatusEffectInstance) {
        val hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.effectType)
        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addStatusEffect(StatusEffectInstance(mapStatusEffect.effectType,
                    mapStatusEffect.duration, mapStatusEffect.amplifier))
        }
    }

    private fun hasFullSuitOfArmorOn(player: PlayerEntity): Boolean {
        val boots = player.inventory.getArmorStack(0)
        val leggings = player.inventory.getArmorStack(1)
        val chestplate = player.inventory.getArmorStack(2)
        val helmet = player.inventory.getArmorStack(3)
        return (!helmet.isEmpty && !chestplate.isEmpty
                && !leggings.isEmpty && !boots.isEmpty)
    }

    private fun hasCorrectArmorOn(material: ArmorMaterial, player: PlayerEntity): Boolean {
        for (armorStack in player.inventory.armor) {
            if (armorStack.item !is ArmorItem) {
                return false
            }
        }
        val boots = player.inventory.getArmorStack(0).item as ArmorItem
        val leggings = player.inventory.getArmorStack(1).item as ArmorItem
        val chestplate = player.inventory.getArmorStack(2).item as ArmorItem
        val helmet = player.inventory.getArmorStack(3).item as ArmorItem
        return helmet.material === material && chestplate.material === material && leggings.material === material && boots.material === material
    }

    companion object {
        private val MATERIAL_TO_EFFECT_MAP: Map<ArmorMaterial, StatusEffectInstance> = ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>()
                .put(ModArmorMaterials.MYTHRIL,
                        StatusEffectInstance(StatusEffects.LUCK, 400, 1)).build()
    }
}