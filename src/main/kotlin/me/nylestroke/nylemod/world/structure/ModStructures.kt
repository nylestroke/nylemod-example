package me.nylestroke.nylemod.world.structure

import com.mojang.serialization.Codec
import me.nylestroke.nylemod.NylemodExample
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.structure.StructureType

object ModStructures {
    var SKY_STRUCTURES: StructureType<*>? = null

    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It's great for mod/datapacks compatibility.
     */
    fun registerStructureFeatures() {
        SKY_STRUCTURES = register<SkyStructures>(Identifier(NylemodExample.MOD_ID, "sky_structures"), SkyStructures.Companion.CODEC)
    }

    // Helper method to register since compiler will complain about typing if we did () -> SkyStructures.CODEC directly.
    private fun <S : Structure?> register(id: Identifier, codec: Codec<S>): StructureType<S> {
        return Registry.register<StructureType<*>, StructureType<S>>(Registry.STRUCTURE_TYPE, id, StructureType<S> { codec })
    }
}