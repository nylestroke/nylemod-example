package me.nylestroke.nylemod.block.entity.client

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.entity.GoldenStandEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel

class GoldenStandModel : AnimatedGeoModel<GoldenStandEntity?>() {
    override fun getModelResource(`object`: GoldenStandEntity?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "geo/golden_stand.geo.json")
    }

    override fun getTextureResource(`object`: GoldenStandEntity?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "textures/machines/golden_stand.png")
    }

    override fun getAnimationResource(animatable: GoldenStandEntity?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "animations/golden_stand.animation.json")
    }
}