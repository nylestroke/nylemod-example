package me.nylestroke.nylemod.particle;

import me.nylestroke.nylemod.NylemodExample;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static final DefaultParticleType CITRINE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {

        Registry.register(Registry.PARTICLE_TYPE, new Identifier(NylemodExample.MOD_ID, "citrine_particle"),
                CITRINE_PARTICLE);

    }

}
