package me.nylestroke.nylemod.villager

import com.google.common.collect.ImmutableSet
import me.nylestroke.nylemod.NylemodExample
import net.fabricmc.fabric.api.`object`.builder.v1.villager.VillagerProfessionBuilder
import net.minecraft.block.Block
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType

object ModVillagers {
    val BLAST_MASTER: VillagerProfession = registerProfession("blastmaster",
            RegistryKey.of<PointOfInterestType>(Registry.POINT_OF_INTEREST_TYPE_KEY, Identifier(NylemodExample.MOD_ID, "blasterpoi")))

    fun registerProfession(name: String?, type: RegistryKey<PointOfInterestType?>?): VillagerProfession {
        return Registry.register<VillagerProfession, VillagerProfession>(Registry.VILLAGER_PROFESSION, Identifier(NylemodExample.MOD_ID, name),
                VillagerProfessionBuilder.create().id(Identifier(NylemodExample.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build())
    }

    fun registerPOI(name: String?, block: Block): PointOfInterestType {
        return Registry.register<PointOfInterestType, PointOfInterestType>(Registry.POINT_OF_INTEREST_TYPE, Identifier(NylemodExample.MOD_ID, name),
                PointOfInterestType(ImmutableSet.copyOf(block.stateManager.states), 1, 1))
    }
}