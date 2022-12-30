package me.nylestroke.nylemod.particle.custom

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType

class CitrineParticle(level: ClientWorld?, xCoord: Double, yCoord: Double, zCoord: Double,
                      spriteSet: SpriteProvider?, xd: Double, yd: Double, zd: Double) : SpriteBillboardParticle(level, xCoord, yCoord, zCoord, xd, yd, zd) {
    init {
        this.velocityMultiplier = 0.6f
        this.x = xd
        this.y = yd
        this.z = zd
        this.scale *= 0.75f
        this.maxAge = 20
        this.setSpriteForAge(spriteSet)
        this.red = 1f
        this.green = 1f
        this.blue = 1f
    }

    override fun tick() {
        super.tick()
        fadeOut()
    }

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT
    }

    private fun fadeOut() {
        this.alpha = -(1 / maxAge.toFloat()) * age + 1
    }

    @Environment(EnvType.CLIENT)
    class Factory(spriteSet: SpriteProvider) : ParticleFactory<DefaultParticleType?> {
        private val sprites: SpriteProvider

        init {
            sprites = spriteSet
        }

        override fun createParticle(
            parameters: DefaultParticleType?,
            level: ClientWorld?,
            x: Double,
            y: Double,
            z: Double,
            dx: Double,
            dy: Double,
            dz: Double
        ): Particle? {
            return CitrineParticle(level, x, y, z, sprites, dx, dy, dz)
        }
    }
}