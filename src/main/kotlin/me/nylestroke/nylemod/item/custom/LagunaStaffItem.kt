package me.nylestroke.nylemod.item.custom

import net.minecraft.item.Item
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class LagunaStaffItem(settings: Settings?) : Item(settings), IAnimatable {
    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(AnimationBuilder().addAnimation("idle", true))
        return PlayState.CONTINUE
    }

    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController<IAnimatable?>(this, "controller",
                0f, AnimationController.IAnimationPredicate { event: AnimationEvent<*> -> predicate(event) }))
    }

    override fun getFactory(): AnimationFactory {
        return AnimationFactory(this)
    }
}