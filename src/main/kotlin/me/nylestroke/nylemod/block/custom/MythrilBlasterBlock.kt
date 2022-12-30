package me.nylestroke.nylemod.block.custom

import me.nylestroke.nylemod.block.entity.ModBlockEntities
import me.nylestroke.nylemod.block.entity.MythrilBlasterBlockEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.*
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.function.BinaryOperator
import java.util.stream.Stream

class MythrilBlasterBlock(settings: Settings) : BlockWithEntity(settings), BlockEntityProvider {
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape {
        return when (state.get<Direction>(FACING)) {
            Direction.NORTH -> SHAPE_N
            Direction.SOUTH -> SHAPE_S
            Direction.WEST -> SHAPE_W
            Direction.EAST -> SHAPE_E
            else -> SHAPE_N
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return this.defaultState.with<Direction, Direction>(FACING, ctx.playerFacing.opposite)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with<Direction, Direction>(FACING, rotation.rotate(state.get<Direction>(FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get<Direction>(FACING)))
    }

    protected override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    /* BLOCK ENTITY */
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (state.block !== newState.block) {
            val blockEntity: BlockEntity? = world.getBlockEntity(pos)
            if (blockEntity is MythrilBlasterBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity as MythrilBlasterBlockEntity?)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos,
                       player: PlayerEntity, hand: Hand, hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient()) {
            val screenHandlerFactory: NamedScreenHandlerFactory? = state.createScreenHandlerFactory(world, pos)
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return MythrilBlasterBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(world: World, state: BlockState, type: BlockEntityType<T>):
            BlockEntityTicker<T>? {
        return BlockWithEntity.checkType<MythrilBlasterBlockEntity, T>(type, ModBlockEntities.MYTHRIL_BLASTER,
            BlockEntityTicker<MythrilBlasterBlockEntity> { world: World?, pos: BlockPos?, state: BlockState?,
                                                           entity: MythrilBlasterBlockEntity? ->
            if (entity != null) {
                MythrilBlasterBlockEntity.Companion.tick(world, pos, state, entity)
            }
        })
    }

    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
        private val SHAPE_N: VoxelShape = Stream.of(
                Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
                Block.createCuboidShape(3.0, 2.0, 4.0, 13.0, 6.0, 5.0),
                Block.createCuboidShape(2.0, 0.0, 1.0, 14.0, 2.0, 14.0),
                Block.createCuboidShape(3.0, 0.0, 15.0, 13.0, 2.0, 16.0),
                Block.createCuboidShape(3.0, 2.0, 5.0, 13.0, 14.0, 14.0),
                Block.createCuboidShape(3.0, 0.0, 14.0, 13.0, 7.0, 15.0),
                Block.createCuboidShape(4.0, 13.0, 7.0, 12.0, 15.0, 13.0)
        ).reduce(BinaryOperator<VoxelShape> { v1: VoxelShape?, v2: VoxelShape? ->
            VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }).get()
        private val SHAPE_E: VoxelShape = Stream.of(
                Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
                Block.createCuboidShape(11.0, 2.0, 3.0, 12.0, 6.0, 13.0),
                Block.createCuboidShape(2.0, 0.0, 2.0, 15.0, 2.0, 14.0),
                Block.createCuboidShape(0.0, 0.0, 3.0, 1.0, 2.0, 13.0),
                Block.createCuboidShape(2.0, 2.0, 3.0, 11.0, 14.0, 13.0),
                Block.createCuboidShape(1.0, 0.0, 3.0, 2.0, 7.0, 13.0),
                Block.createCuboidShape(3.0, 13.0, 4.0, 9.0, 15.0, 12.0)
        ).reduce(BinaryOperator<VoxelShape> { v1: VoxelShape?, v2: VoxelShape? ->
            VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }).get()
        private val SHAPE_S: VoxelShape = Stream.of(
                Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
                Block.createCuboidShape(3.0, 2.0, 11.0, 13.0, 6.0, 12.0),
                Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 2.0, 15.0),
                Block.createCuboidShape(3.0, 0.0, 0.0, 13.0, 2.0, 1.0),
                Block.createCuboidShape(3.0, 2.0, 2.0, 13.0, 14.0, 11.0),
                Block.createCuboidShape(3.0, 0.0, 1.0, 13.0, 7.0, 2.0),
                Block.createCuboidShape(4.0, 13.0, 3.0, 12.0, 15.0, 9.0)
        ).reduce(BinaryOperator<VoxelShape> { v1: VoxelShape?, v2: VoxelShape? ->
            VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }).get()
        private val SHAPE_W: VoxelShape = Stream.of(
                Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
                Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
                Block.createCuboidShape(4.0, 2.0, 3.0, 5.0, 6.0, 13.0),
                Block.createCuboidShape(1.0, 0.0, 2.0, 14.0, 2.0, 14.0),
                Block.createCuboidShape(15.0, 0.0, 3.0, 16.0, 2.0, 13.0),
                Block.createCuboidShape(5.0, 2.0, 3.0, 14.0, 14.0, 13.0),
                Block.createCuboidShape(14.0, 0.0, 3.0, 15.0, 7.0, 13.0),
                Block.createCuboidShape(7.0, 13.0, 4.0, 13.0, 15.0, 12.0)
        ).reduce(BinaryOperator<VoxelShape> { v1: VoxelShape?, v2: VoxelShape? ->
            VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }).get()
    }
}