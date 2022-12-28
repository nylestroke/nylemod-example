package me.nylestroke.nylemod.entity.client.block;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class GoldenStandRenderer extends GeoBlockRenderer<GoldenStandEntity> {

    public GoldenStandRenderer(BlockEntityRendererFactory.Context context) {
        super(new GoldenStandModel());
    }

    @Override
    public RenderLayer getRenderType(GoldenStandEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }

}
