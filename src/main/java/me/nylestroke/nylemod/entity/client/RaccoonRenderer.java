package me.nylestroke.nylemod.entity.client;

import com.google.common.collect.Maps;
import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.entity.custom.RaccoonEntity;
import me.nylestroke.nylemod.entity.variant.RaccoonVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity> {

    public RaccoonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RaccoonModel());
    }

    public static final Map<RaccoonVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RaccoonVariant.class), (map) -> {
                map.put(RaccoonVariant.DEFAULT,
                        new Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/raccoon.png"));
                map.put(RaccoonVariant.DARK,
                        new Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/raccoondark.png"));
                map.put(RaccoonVariant.RED,
                        new Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/redraccoon.png"));
            });

    @Override
    public Identifier getTextureLocation(RaccoonEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderLayer getRenderType(RaccoonEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {

        if (animatable.isBaby()) {
            stack.scale(0.5f, 0.5f, 0.5f);
        } else {
            stack.scale(1f, 1f, 1f);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer,
                vertexBuilder, packedLightIn, textureLocation);
    }
}
