package me.nylestroke.nylemod.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.*
import net.minecraft.server.world.ServerWorld

class LightingStrikerEnchantment(weight: Enchantment.Rarity?, type: EnchantmentTarget?, vararg slotTypes: EquipmentSlot?) : Enchantment(weight, type, slotTypes) {
    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (!user.world.isClient()) {
            val world: ServerWorld = user.world as ServerWorld
            val position = target.blockPos
            if (level == 1) {
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, null, position,
                        SpawnReason.TRIGGERED, true, true)
            }
            if (level == 2) {
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, null, position,
                        SpawnReason.TRIGGERED, true, true)
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, null, position,
                        SpawnReason.TRIGGERED, true, true)
            }
        }
        super.onTargetDamaged(user, target, level)
    }

    override fun getMaxLevel(): Int {
        return 2
    }
}