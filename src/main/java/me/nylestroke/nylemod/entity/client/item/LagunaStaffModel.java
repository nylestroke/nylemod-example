package me.nylestroke.nylemod.entity.client.item;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.item.custom.LagunaStaffItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LagunaStaffModel extends AnimatedGeoModel<LagunaStaffItem> {

    @Override
    public Identifier getModelLocation(LagunaStaffItem object) {
        return new Identifier(NylemodExample.MOD_ID, "geo/laguna_staff.geo.json");
    }

    @Override
    public Identifier getTextureLocation(LagunaStaffItem object) {
        return new Identifier(NylemodExample.MOD_ID, "textures/item/laguna_staff_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(LagunaStaffItem animatable) {
        return new Identifier(NylemodExample.MOD_ID, "animations/laguna_staff.animation.json");
    }

}
