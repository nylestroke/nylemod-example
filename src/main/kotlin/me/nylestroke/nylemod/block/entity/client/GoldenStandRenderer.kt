package me.nylestroke.nylemod.block.entity.client

import me.nylestroke.nylemod.block.entity.GoldenStandEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer

class GoldenStandRenderer(context: BlockEntityRendererFactory.Context) : GeoBlockRenderer<GoldenStandEntity>(GoldenStandModel()) {
    override fun getRenderType(
        animatable: GoldenStandEntity,
        partialTicks: Float,
        stack: MatrixStack,
        renderTypeBuffer: VertexConsumerProvider?,
        vertexBuilder: VertexConsumer?,
        packedLightIn: Int,
        textureLocation: Identifier
    ): RenderLayer {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable))
    }
}