package me.nylestroke.nylemod.item.custom

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class GoldenStandItem(block: Block?, settings: Settings?) : BlockItem(block, settings), IAnimatable {
    private var factory = AnimationFactory(this)
    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        // event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE
    }

    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController<IAnimatable?>(this, "controller",
                0f, AnimationController.IAnimationPredicate { event: AnimationEvent<*> -> predicate(event) }))
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }
}