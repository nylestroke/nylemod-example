package me.nylestroke.nylemod.armor.client

import me.nylestroke.nylemod.item.MythrilArmorItem
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer

class MythrilArmorRenderer : GeoArmorRenderer<MythrilArmorItem?>(MythrilArmorModel()) {
    init {
        this.headBone = "armorHead"
        this.bodyBone = "armorBody"
        this.rightArmBone = "armorRightArm"
        this.leftArmBone = "armorLeftArm"
        this.rightLegBone = "armorLeftLeg"
        this.leftLegBone = "armorRightLeg"
        this.rightBootBone = "armorLeftBoot"
        this.leftBootBone = "armorRightBoot"
    }
}