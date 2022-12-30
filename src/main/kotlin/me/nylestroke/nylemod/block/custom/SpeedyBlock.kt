package me.nylestroke.nylemod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World

class SpeedyBlock(settings: Settings) : Block(settings) {
    override fun onUse(state: BlockState, world: World, pos: BlockPos,
                       player: PlayerEntity, hand: Hand, hit: BlockHitResult
    ): ActionResult {
        if (world.isClient()) {
            if (hand == Hand.MAIN_HAND) {
                player.sendMessage(Text.literal("CLIENT: This is THE CLIENT! MAIN HAND!"), false)
            } else {
                player.sendMessage(Text.literal("CLIENT: This is THE CLIENT! OFF HAND!"), false)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun appendTooltip(stack: ItemStack, world: BlockView?, tooltip: MutableList<Text>,
                               options: TooltipContext) {
        tooltip.add(Text.translatable("item.nylemod.speedy_block.tooltip"))
    }

    override fun onSteppedOn(world: World, pos: BlockPos, state: BlockState, entity: Entity) {
        if (!world.isClient()) {
            if (entity is LivingEntity) {
                entity.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, 300))
            }
        }
        super.onSteppedOn(world, pos, state, entity)
    }
}