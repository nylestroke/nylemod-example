package me.nylestroke.nylemod.entity.client.armor;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.item.MythrilArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MythrilArmorModel extends AnimatedGeoModel<MythrilArmorItem> {

    @Override
    public Identifier getModelLocation(MythrilArmorItem object) {
        return new Identifier(NylemodExample.MOD_ID, "geo/mythril_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MythrilArmorItem object) {
        return new Identifier(NylemodExample.MOD_ID, "textures/models/armor/mythril_armor_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(MythrilArmorItem animatable) {
        return new Identifier(NylemodExample.MOD_ID, "animations/armor_animation.json");
    }

}
