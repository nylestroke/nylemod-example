package me.nylestroke.nylemod.entity.client.item;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.item.custom.GoldenStandItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoldenStandItemModel extends AnimatedGeoModel<GoldenStandItem> {

    @Override
    public Identifier getModelLocation(GoldenStandItem object) {
        return new Identifier(NylemodExample.MOD_ID, "geo/golden_stand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GoldenStandItem object) {
        return new Identifier(NylemodExample.MOD_ID, "textures/machines/golden_stand.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GoldenStandItem animatable) {
        return new Identifier(NylemodExample.MOD_ID, "animations/golden_stand.animation.json");
    }
    
}
