package me.nylestroke.nylemod.entity.client.item;

import me.nylestroke.nylemod.item.custom.GoldenStandItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GoldenStandItemRenderer extends GeoItemRenderer<GoldenStandItem> {

    public GoldenStandItemRenderer() {
        super(new GoldenStandItemModel());
    }

}
