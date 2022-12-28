package me.nylestroke.nylemod.block.entity;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.block.ModBlocks;
import me.nylestroke.nylemod.entity.client.block.GoldenStandEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<MythrilBlasterBlockEntity> MYTHRIL_BLASTER;

    public static BlockEntityType<GoldenStandEntity> GOLDEN_STAND_ENTITY;

    public static void registerAllBlockEntities() {

        MYTHRIL_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NylemodExample.MOD_ID, "mythril_blaster"),
                FabricBlockEntityTypeBuilder.create(MythrilBlasterBlockEntity::new,
                        ModBlocks.MYTHRIL_BLASTER).build(null));

        GOLDEN_STAND_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NylemodExample.MOD_ID, "golden_stand_entity"),
                FabricBlockEntityTypeBuilder.create(GoldenStandEntity::new,
                        ModBlocks.GOLDEN_STAND).build(null));

    }

}
