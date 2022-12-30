package me.nylestroke.nylemod.item.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import software.bernie.example.entity.RocketProjectile

class MythrilStaffItem(settings: Settings?) : Item(settings) {
    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        player.itemCooldownManager[this] = 8
        if (!world.isClient()) {
            val arrowEntity = RocketProjectile(world, player)
            arrowEntity.setVelocity(player, player.pitch, player.yaw, 0.0f,
                    1.0f * 3.0f, 1.0f)
            arrowEntity.damage = 2.5
            arrowEntity.age = 35
            arrowEntity.hasNoGravity()
            world.spawnEntity(arrowEntity)
        }
        return super.use(world, player, hand)
    }
}