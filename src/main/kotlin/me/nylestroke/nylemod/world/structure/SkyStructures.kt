package me.nylestroke.nylemod.world.structure

import com.mojang.datafixers.kinds.App
import com.mojang.datafixers.util.Function7
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.HeightContext
import net.minecraft.world.gen.heightprovider.HeightProvider
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.structure.StructureType
import java.util.*
import java.util.function.Function

class SkyStructures(config: Config?,
                    startPool: RegistryEntry<StructurePool>,
                    startJigsawName: Optional<Identifier>,
                    size: Int,
                    startHeight: HeightProvider,
                    projectStartToHeightmap: Optional<Heightmap.Type>,
                    maxDistanceFromCenter: Int) : Structure(config) {
    private val startPool: RegistryEntry<StructurePool>
    private val startJigsawName: Optional<Identifier>
    private val size: Int
    private val startHeight: HeightProvider
    private val projectStartToHeightmap: Optional<Heightmap.Type>
    private val maxDistanceFromCenter: Int

    init {
        this.startPool = startPool
        this.startJigsawName = startJigsawName
        this.size = size
        this.startHeight = startHeight
        this.projectStartToHeightmap = projectStartToHeightmap
        this.maxDistanceFromCenter = maxDistanceFromCenter
    }

    override fun getStructurePosition(context: Context): Optional<StructurePosition> {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!extraSpawningChecks(context)) {
            return Optional.empty()
        }

        // Set's our spawning blockpos's y offset to be 60 blocks up.
        // Since we are going to have heightmap/terrain height spawning set to true further down, this will make it so we spawn 60 blocks above terrain.
        // If we wanted to spawn on ocean floor, we would set heightmap/terrain height spawning to false and the grab the y value of the terrain with OCEAN_FLOOR_WG heightmap.
        val startY: Int = startHeight.get(context.random(), HeightContext(context.chunkGenerator(), context.world()))

        // Turns the chunk coordinates into actual coordinates we can use. (Gets corner of that chunk)
        val chunkPos: ChunkPos = context.chunkPos()
        val blockPos = BlockPos(chunkPos.startX, startY, chunkPos.startZ)

        /*
         * Note, you are always free to make your own StructurePoolBasedGenerator class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         */

        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return StructurePoolBasedGenerator.generate(
                context,  // Used for StructurePoolBasedGenerator to get all the proper behaviors done.
                startPool,  // The starting pool to use to create the structure layout from
                startJigsawName,  // Can be used to only spawn from one Jigsaw block. But we don't need to worry about this.
                size,  // How deep a branch of pieces can go away from center piece. (5 means branches cannot be longer than 5 pieces from center piece)
                blockPos,  // Where to spawn the structure.
                false,  // "useExpansionHack" This is for legacy villages to generate properly. You should keep this false always.
                projectStartToHeightmap,  // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
                // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
                // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
                // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                maxDistanceFromCenter)
    }

    override fun getType(): StructureType<*>? {
        return ModStructures.SKY_STRUCTURES // Helps the game know how to turn this structure back to json to save to chunks
    }

    companion object {
        // A custom codec that changes the size limit for our code_structure_sky_fan.json's config to not be capped at 7.
        // With this, we can have a structure with a size limit up to 30 if we want to have extremely long branches of pieces in the structure.
        val CODEC: Codec<SkyStructures> = RecordCodecBuilder.mapCodec<SkyStructures>(Function<RecordCodecBuilder.Instance<SkyStructures>, App<RecordCodecBuilder.Mu<SkyStructures>, SkyStructures>> { instance: RecordCodecBuilder.Instance<SkyStructures> ->
            instance.group<Config, RegistryEntry<StructurePool>, Optional<Identifier>, Int, HeightProvider, Optional<Heightmap.Type>, Int>(configCodecBuilder<SkyStructures>(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter<SkyStructures>(Function<SkyStructures, RegistryEntry<StructurePool>> { structure: SkyStructures -> structure.startPool }),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter { structure: SkyStructures -> structure.startJigsawName },
                    Codec.intRange(0, 30).fieldOf("size").forGetter { structure: SkyStructures -> structure.size },
                    HeightProvider.CODEC.fieldOf("start_height").forGetter<SkyStructures>(Function<SkyStructures, HeightProvider> { structure: SkyStructures -> structure.startHeight }),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter<SkyStructures>(Function<SkyStructures, Optional<Heightmap.Type>> { structure: SkyStructures -> structure.projectStartToHeightmap }),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter { structure: SkyStructures -> structure.maxDistanceFromCenter }
            ).apply<SkyStructures>(instance, Function7<Config, RegistryEntry<StructurePool>, Optional<Identifier>, Int, HeightProvider, Optional<Heightmap.Type>, Int, SkyStructures> { config: Config, startPool: RegistryEntry<StructurePool>, startJigsawName: Optional<Identifier>, size: Int, startHeight: HeightProvider, projectStartToHeightmap: Optional<Heightmap.Type>, maxDistanceFromCenter: Int -> SkyStructures(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter) })
        }).codec()

        /*
     * This is where extra checks can be done to determine if the structure can spawn here.
     * This only needs to be overridden if you're adding additional spawn conditions.
     *
     * Fun fact, if you set your structure separation/spacing to be 0/1, you can use
     * isFeatureChunk to return true only if certain chunk coordinates are passed in
     * which allows you to spawn structures only at certain coordinates in the world.
     *
     * Basically, this method is used for determining if the land is at a suitable height,
     * if certain other structures are too close or not, or some other restrictive condition.
     *
     * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
     * (Bedrock Edition seems to not have the same check)
     *
     * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
     * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
     * Then loop through it and look for land with air above it and set blockpos's Y value to it.
     * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
     * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
     *
     * Also, please for the love of god, do not do dimension checking here.
     * If you do and another mod's dimension is trying to spawn your structure,
     * the locate command will make minecraft hang forever and break the game.
     * Use the biome tags for where to spawn the structure and users can datapack
     * it to spawn in specific biomes that aren't in the dimension they don't like if they wish.
     */
        private fun extraSpawningChecks(context: Context): Boolean {
            // Grabs the chunk position we are at
            val chunkpos: ChunkPos = context.chunkPos()

            // Checks to make sure our structure does not spawn above land that's higher than y = 150
            // to demonstrate how this method is good for checking extra conditions for spawning
            return context.chunkGenerator().getHeightInGround(
                    chunkpos.getStartX(),
                    chunkpos.getStartZ(),
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    context.world(),
                    context.noiseConfig()) < 150
        }
    }
}