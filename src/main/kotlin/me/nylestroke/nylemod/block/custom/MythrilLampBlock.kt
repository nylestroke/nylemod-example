package me.nylestroke.nylemod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MythrilLampBlock(settings: Settings) : Block(settings) {
    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity,
                       hand: Hand, hit: BlockHitResult): ActionResult {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            val currentState = state.get<Boolean>(CLICKED)
            world.setBlockState(pos, state.with(CLICKED, !currentState), NOTIFY_ALL)
        }
        return ActionResult.SUCCESS
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(CLICKED)
    }

    companion object {
        val CLICKED: BooleanProperty = BooleanProperty.of("clicked")
    }
}