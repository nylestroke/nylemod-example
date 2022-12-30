package me.nylestroke.nylemod.armor.client

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.item.MythrilArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel

class MythrilArmorModel : AnimatedGeoModel<MythrilArmorItem?>() {

    override fun getModelResource(`object`: MythrilArmorItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "geo/mythril_armor.geo.json")
    }

    override fun getTextureResource(`object`: MythrilArmorItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "textures/models/armor/mythril_armor_texture.png")
    }

    override fun getAnimationResource(animatable: MythrilArmorItem?): Identifier {
        return Identifier(NylemodExample.MOD_ID, "animations/armor_animation.json")
    }
}