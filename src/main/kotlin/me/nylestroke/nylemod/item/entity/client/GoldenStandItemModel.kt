package me.nylestroke.nylemod.item.entity.client

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.item.custom.GoldenStandItem
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel

class GoldenStandItemModel : AnimatedGeoModel<GoldenStandItem?>() {
    override fun getModelResource(`object`: GoldenStandItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "geo/golden_stand.geo.json")
    }

    override fun getTextureResource(`object`: GoldenStandItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "textures/machines/golden_stand.png")
    }

    override fun getAnimationResource(animatable: GoldenStandItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "animations/golden_stand.animation.json")
    }
}