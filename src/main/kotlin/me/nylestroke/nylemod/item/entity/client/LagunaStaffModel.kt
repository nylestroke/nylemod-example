package me.nylestroke.nylemod.item.entity.client

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.item.custom.LagunaStaffItem
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel

class LagunaStaffModel : AnimatedGeoModel<LagunaStaffItem?>() {
    override fun getModelResource(`object`: LagunaStaffItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "geo/laguna_staff.geo.json")
    }

    override fun getTextureResource(`object`: LagunaStaffItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "textures/item/laguna_staff_texture.png")
    }

    override fun getAnimationResource(animatable: LagunaStaffItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "animations/laguna_staff.animation.json")
    }
}