package me.nylestroke.nylemod.entity.client.item;

import me.nylestroke.nylemod.item.custom.LagunaStaffItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class LagunaStaffRenderer extends GeoItemRenderer<LagunaStaffItem> {

    public LagunaStaffRenderer() {
        super(new LagunaStaffModel());
    }

}
