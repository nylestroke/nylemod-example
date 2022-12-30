package me.nylestroke.nylemod.block.entity

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.block.ModBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

object ModBlockEntities {
    var MYTHRIL_BLASTER: BlockEntityType<MythrilBlasterBlockEntity>? = null
    var GOLDEN_STAND_ENTITY: BlockEntityType<GoldenStandEntity>? = null
    fun registerAllBlockEntities() {
        MYTHRIL_BLASTER = Registry.register<BlockEntityType<*>, BlockEntityType<MythrilBlasterBlockEntity>>(Registry.BLOCK_ENTITY_TYPE,
                Identifier(NylemodExample.MOD_ID, "mythril_blaster"),
                FabricBlockEntityTypeBuilder.create<MythrilBlasterBlockEntity>(FabricBlockEntityTypeBuilder.Factory<MythrilBlasterBlockEntity> { pos: BlockPos?, state: BlockState? -> MythrilBlasterBlockEntity(pos, state) },
                        ModBlocks.MYTHRIL_BLASTER).build(null))
        GOLDEN_STAND_ENTITY = Registry.register<BlockEntityType<*>, BlockEntityType<GoldenStandEntity>>(Registry.BLOCK_ENTITY_TYPE,
                Identifier(NylemodExample.MOD_ID, "golden_stand_entity"),
                FabricBlockEntityTypeBuilder.create<GoldenStandEntity>(FabricBlockEntityTypeBuilder.Factory<GoldenStandEntity> { pos: BlockPos?, state: BlockState? -> GoldenStandEntity(pos, state) },
                        ModBlocks.GOLDEN_STAND).build(null))
    }
}