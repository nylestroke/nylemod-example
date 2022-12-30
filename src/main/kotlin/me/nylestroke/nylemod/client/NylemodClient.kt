package me.nylestroke.nylemod.client

import me.nylestroke.nylemod.armor.client.MythrilArmorRenderer
import me.nylestroke.nylemod.block.ModBlocks
import me.nylestroke.nylemod.block.entity.GoldenStandEntity
import me.nylestroke.nylemod.block.entity.ModBlockEntities
import me.nylestroke.nylemod.block.entity.client.GoldenStandRenderer
import me.nylestroke.nylemod.entity.ModEntities
import me.nylestroke.nylemod.entity.client.RaccoonRenderer
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import me.nylestroke.nylemod.fluid.ModFluids
import me.nylestroke.nylemod.item.ModItems
import me.nylestroke.nylemod.item.entity.client.GoldenStandItemRenderer
import me.nylestroke.nylemod.item.entity.client.LagunaStaffRenderer
import me.nylestroke.nylemod.particle.ModParticles
import me.nylestroke.nylemod.screen.ModScreenHandlers
import me.nylestroke.nylemod.screen.MythrilBlasterScreen
import me.nylestroke.nylemod.screen.MythrilBlasterScreenHandler
import me.nylestroke.nylemod.util.ModModelPredicateProvider
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.particle.AshParticle
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.particle.DefaultParticleType
import net.minecraft.text.Text
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer

class NylemodClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VLADIK_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VLADIK_TRAPDOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LILAC_FLOWER, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILAC_FLOWER, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_WINDOW, RenderLayer.getTranslucent())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_VINE, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_LEAVES, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYTHRIL_BLASTER, RenderLayer.getCutout())
        ModModelPredicateProvider.registerModModels()
        ScreenRegistry.register<MythrilBlasterScreenHandler, MythrilBlasterScreen>(ModScreenHandlers.MYTHRIL_BLASTER_SCREEN_HANDLER, ScreenRegistry.Factory<MythrilBlasterScreenHandler, MythrilBlasterScreen> { handler: MythrilBlasterScreenHandler, inventory: PlayerInventory, title: Text -> MythrilBlasterScreen(handler, inventory, title) })
        ParticleFactoryRegistry.getInstance().register<DefaultParticleType>(ModParticles.CITRINE_PARTICLE,
            ParticleFactoryRegistry.PendingParticleFactory<DefaultParticleType> { spriteSet: FabricSpriteProvider ->
                AshParticle.Factory(spriteSet)
            })
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_STILL,
                SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c)
        )
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_FLOWING,
                SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c))
        EntityRendererRegistry.register<RaccoonEntity>(ModEntities.RACCOON, EntityRendererFactory<RaccoonEntity>
        { ctx: EntityRendererFactory.Context -> RaccoonRenderer(ctx) })
        GeoArmorRenderer.registerArmorRenderer<Entity>(
            MythrilArmorRenderer(), ModItems.MYTHRIL_BOOTS,
                ModItems.MYTHRIL_LEGGINGS, ModItems.MYTHRIL_CHESTPLATE, ModItems.MYTHRIL_HELMET)
        GeoItemRenderer.registerItemRenderer(ModItems.LAGUNA_STAFF, LagunaStaffRenderer())
        GeoItemRenderer.registerItemRenderer(ModItems.GOLDEN_STAND_ITEM, GoldenStandItemRenderer())
        BlockEntityRendererRegistry.register<GoldenStandEntity>(ModBlockEntities.GOLDEN_STAND_ENTITY,
            BlockEntityRendererFactory<BlockEntity> { context: BlockEntityRendererFactory.Context ->
                GoldenStandRenderer(context) })
    }
}