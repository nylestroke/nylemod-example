package me.nylestroke.nylemod.world.gen;

public class ModWorldGen {

    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();

        ModFlowerGeneration.generateFlowers();
        ModTreeGeneration.generateTrees();

        ModEntitySpawn.addEntitySpawn();

    }

}
