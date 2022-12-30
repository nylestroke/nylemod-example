package me.nylestroke.nylemod.item

import me.nylestroke.nylemod.item.custom.ModArmorItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import java.util.*
import java.util.function.Consumer

class MythrilArmorItem(material: ArmorMaterial?, slot: EquipmentSlot?, settings: Settings?) : ModArmorItem(material, slot, settings), IAnimatable {
    private val factory = AnimationFactory(this)
    private fun <P : IAnimatable?> predicate(event: AnimationEvent<P>): PlayState {
        val livingEntity = event.getExtraDataOfType(LivingEntity::class.java)[0]
        event.controller.setAnimation(AnimationBuilder().addAnimation("idle", true))
        if (livingEntity is ArmorStandEntity) {
            return PlayState.CONTINUE
        } else if (livingEntity is PlayerEntity) {
            val equipmentList: MutableList<Item?> = ArrayList()
            livingEntity.itemsEquipped.forEach(Consumer { x: ItemStack -> equipmentList.add(x.item) })
            val armorList: List<Item?> = equipmentList.subList(2, 6)
            val isWearingAll = armorList.containsAll(Arrays.asList(ModItems.MYTHRIL_BOOTS,
                    ModItems.MYTHRIL_LEGGINGS, ModItems.MYTHRIL_CHESTPLATE, ModItems.MYTHRIL_HELMET))
            return if (isWearingAll) PlayState.CONTINUE else PlayState.STOP
        }
        return PlayState.STOP
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController<IAnimatable?>(this, "controller", 20f, AnimationController.IAnimationPredicate { event: AnimationEvent<*> -> predicate(event) }))
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }
}