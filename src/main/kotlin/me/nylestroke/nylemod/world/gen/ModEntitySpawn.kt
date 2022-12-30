package me.nylestroke.nylemod.world.gen

import me.nylestroke.nylemod.entity.ModEntities
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.biome.BiomeKeys

object ModEntitySpawn {
    fun addEntitySpawn() {
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                SpawnGroup.CREATURE, ModEntities.RACCOON, 25, 2, 5)
        SpawnRestriction.register<RaccoonEntity>(ModEntities.RACCOON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            SpawnRestriction.SpawnPredicate<RaccoonEntity> { type: EntityType<RaccoonEntity?>?, world: ServerWorldAccess?, spawnReason: SpawnReason?, pos: BlockPos?, random: Random? ->
                AnimalEntity.isValidNaturalSpawn(
                    type,
                    world,
                    spawnReason,
                    pos,
                    random
                )
            })
    }
}