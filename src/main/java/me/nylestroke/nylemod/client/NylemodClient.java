package me.nylestroke.nylemod.client;

import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.block.entity.ModBlockEntities;
import me.nylestroke.nylemod.entity.ModEntities;
import me.nylestroke.nylemod.entity.client.RaccoonRenderer;
import me.nylestroke.nylemod.entity.client.armor.MythrilArmorRenderer;
import me.nylestroke.nylemod.entity.client.block.GoldenStandRenderer;
import me.nylestroke.nylemod.entity.client.item.GoldenStandItemRenderer;
import me.nylestroke.nylemod.entity.client.item.LagunaStaffRenderer;
import me.nylestroke.nylemod.fluid.ModFluids;
import me.nylestroke.nylemod.item.ModItems;
import me.nylestroke.nylemod.particle.ModParticles;
import me.nylestroke.nylemod.particle.custom.CitrineParticle;
import me.nylestroke.nylemod.screen.ModScreenHandlers;
import me.nylestroke.nylemod.screen.MythrilBlasterScreen;
import me.nylestroke.nylemod.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class NylemodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VLADIK_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VLADIK_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LILAC_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILAC_FLOWER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_WINDOW, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_VINE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYTHRIL_BLASTER, RenderLayer.getCutout());

        ModModelPredicateProvider.registerModModels();

        ScreenRegistry.register(ModScreenHandlers.MYTHRIL_BLASTER_SCREEN_HANDLER, MythrilBlasterScreen::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CITRINE_PARTICLE, CitrineParticle.Factory::new);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_STILL,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_FLOWING,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));

        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new MythrilArmorRenderer(), ModItems.MYTHRIL_BOOTS,
                ModItems.MYTHRIL_LEGGINGS, ModItems.MYTHRIL_CHESTPLATE, ModItems.MYTHRIL_HELMET);

        GeoItemRenderer.registerItemRenderer(ModItems.LAGUNA_STAFF, new LagunaStaffRenderer());


        GeoItemRenderer.registerItemRenderer(ModItems.GOLDEN_STAND_ITEM, new GoldenStandItemRenderer());
        BlockEntityRendererRegistry.register(ModBlockEntities.GOLDEN_STAND_ENTITY, GoldenStandRenderer::new);
    }
}
