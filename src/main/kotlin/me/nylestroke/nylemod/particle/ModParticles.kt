package me.nylestroke.nylemod.particle

import me.nylestroke.nylemod.NylemodExample
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModParticles {
    val CITRINE_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    fun registerParticles() {
        Registry.register<ParticleType<*>, DefaultParticleType>(Registry.PARTICLE_TYPE, Identifier(NylemodExample.MOD_ID, "citrine_particle"),
                CITRINE_PARTICLE)
    }
}