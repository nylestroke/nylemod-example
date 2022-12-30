package me.nylestroke.nylemod.entity.client

import com.google.common.collect.Maps
import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import me.nylestroke.nylemod.entity.variant.RaccoonVariant
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer
import java.util.*
import java.util.function.Consumer

class RaccoonRenderer(ctx: EntityRendererFactory.Context?) : GeoEntityRenderer<RaccoonEntity?>(ctx, RaccoonModel()) {
    override fun getTextureResource(instance: RaccoonEntity?): Identifier? {
        return LOCATION_BY_VARIANT[instance?.variant]!!
    }

    override fun getRenderType(
        animatable: RaccoonEntity?,
        partialTicks: Float,
        stack: MatrixStack?,
        renderTypeBuffer: VertexConsumerProvider?,
        vertexBuilder: VertexConsumer?,
        packedLightIn: Int,
        textureLocation: Identifier?
    ): RenderLayer? {
        if (animatable!!.isBaby) {
            stack?.scale(0.5f, 0.5f, 0.5f)
        } else {
            stack?.scale(1f, 1f, 1f)
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer,
                vertexBuilder, packedLightIn, textureLocation)
    }

    companion object {
        val LOCATION_BY_VARIANT: Map<RaccoonVariant, Identifier> = Util.make(Maps.newEnumMap<RaccoonVariant, Identifier>(RaccoonVariant::class.java), Consumer { map: EnumMap<RaccoonVariant, Identifier> ->
            map.put(RaccoonVariant.DEFAULT,
                    Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/raccoon.png"))
            map.put(RaccoonVariant.DARK,
                    Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/raccoondark.png"))
            map.put(RaccoonVariant.RED,
                    Identifier(NylemodExample.MOD_ID, "textures/entity/raccoon/redraccoon.png"))
        })
    }
}