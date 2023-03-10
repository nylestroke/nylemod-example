package me.nylestroke.nylemod.entity.client.armor;

import me.nylestroke.nylemod.item.MythrilArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MythrilArmorRenderer extends GeoArmorRenderer<MythrilArmorItem> {

    public MythrilArmorRenderer() {
        super(new MythrilArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }

}