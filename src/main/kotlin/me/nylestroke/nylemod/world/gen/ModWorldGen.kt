package me.nylestroke.nylemod.world.gen

object ModWorldGen {
    fun generateModWorldGen() {
        ModOreGeneration.generateOres()
        ModFlowerGeneration.generateFlowers()
        ModTreeGeneration.generateTrees()
        ModEntitySpawn.addEntitySpawn()
    }
}