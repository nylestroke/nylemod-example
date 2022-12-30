package me.nylestroke.nylemod.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class GoldenStandEntity(pos: BlockPos?, state: BlockState?) : BlockEntity(ModBlockEntities.GOLDEN_STAND_ENTITY, pos, state), IAnimatable {
    private val factory = AnimationFactory(this)
    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController(this, "controller", 0f) { event: AnimationEvent<GoldenStandEntity> -> predicate(event) })
    }

    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(AnimationBuilder().addAnimation("idle", true))
        return PlayState.CONTINUE
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }
}