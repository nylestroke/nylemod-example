package me.nylestroke.nylemod.entity.client.block;

import me.nylestroke.nylemod.NylemodExample;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoldenStandModel extends AnimatedGeoModel<GoldenStandEntity> {

    @Override
    public Identifier getModelLocation(GoldenStandEntity object) {
        return new Identifier(NylemodExample.MOD_ID, "geo/golden_stand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GoldenStandEntity object) {
        return new Identifier(NylemodExample.MOD_ID, "textures/machines/golden_stand.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GoldenStandEntity animatable) {
        return new Identifier(NylemodExample.MOD_ID, "animations/golden_stand.animation.json");
    }

}
