package me.nylestroke.nylemod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SaplingBlock
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import java.util.function.Supplier

class ModSaplingBlock(generator: SaplingGenerator, settings: Settings, private val ground: Supplier<Block>) :
        SaplingBlock(generator, settings) {
    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return floor.isOf(ground.get())
    }
}