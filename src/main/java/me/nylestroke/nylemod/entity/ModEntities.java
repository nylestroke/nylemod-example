package me.nylestroke.nylemod.entity;

import me.nylestroke.nylemod.NylemodExample;
import me.nylestroke.nylemod.entity.custom.RaccoonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<RaccoonEntity> RACCOON = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NylemodExample.MOD_ID, "raccoon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RaccoonEntity::new).dimensions(
                    EntityDimensions.fixed(0.4f, 0.3f)).build()
    );



}
