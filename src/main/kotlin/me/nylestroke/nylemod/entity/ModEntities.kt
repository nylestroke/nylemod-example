package me.nylestroke.nylemod.entity

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.entity.custom.RaccoonEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object ModEntities {
    val RACCOON: EntityType<RaccoonEntity> = Registry.register<EntityType<*>, EntityType<RaccoonEntity>>(
            Registry.ENTITY_TYPE, Identifier(NylemodExample.MOD_ID, "raccoon"),
            FabricEntityTypeBuilder.create<RaccoonEntity>(SpawnGroup.CREATURE, EntityType.EntityFactory<RaccoonEntity> { entityType: EntityType<RaccoonEntity>, world: World -> RaccoonEntity(entityType, world) }).dimensions(
                    EntityDimensions.fixed(0.4f, 0.3f)).build()
    )
}