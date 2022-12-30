package me.nylestroke.nylemod.entity.client

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.model.provider.data.EntityModelData

class RaccoonModel : AnimatedGeoModel<RaccoonEntity?>() {
    override fun getModelResource(`object`: RaccoonEntity?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "geo/raccoon.geo.json")
    }

    override fun getTextureResource(`object`: RaccoonEntity?): Identifier {
        return RaccoonRenderer.Companion.LOCATION_BY_VARIANT[`object`!!.variant]!!
    }

    override fun getAnimationResource(animatable: RaccoonEntity?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "animations/raccoon.animation.json")
    }

    override fun setLivingAnimations(entity: RaccoonEntity?, uniqueID: Int, customPredicate: AnimationEvent<*>) {
        super.setLivingAnimations(entity, uniqueID, customPredicate)
        val head = animationProcessor.getBone("head")
        val extraData: EntityModelData = customPredicate.getExtraDataOfType(EntityModelData::class.java).get(0) as EntityModelData
        if (head != null) {
            head.rotationX = extraData.headPitch * (Math.PI.toFloat() / 180f)
            head.rotationY = extraData.netHeadYaw * (Math.PI.toFloat() / 180f)
        }
    }
}