package me.nylestroke.nylemod.item.custom

import me.nylestroke.nylemod.item.ModItems
import me.nylestroke.nylemod.particle.ModParticles
import me.nylestroke.nylemod.sound.ModSounds
import me.nylestroke.nylemod.util.InventoryUtil
import me.nylestroke.nylemod.util.ModTags
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class DowsingRodItem(settings: Settings?) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient()) {
            val positionClicked = context.blockPos
            val player = context.player
            var foundBlock = false
            for (i in 0..positionClicked.y + 64) {
                val blockBelow = context.world.getBlockState(positionClicked.down(i))
                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.down(i), player, blockBelow.block)
                    foundBlock = true
                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addNbtToDataTable(player, positionClicked.add(0, -i, 0), blockBelow.block)
                    }
                    spawnFoundParticles(context, positionClicked)
                    context.world.playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE,
                            SoundCategory.BLOCKS, 1f, 1f)
                    break
                }
            }
            if (!foundBlock) {
                player!!.sendMessage(Text.translatable("item.nylemod.dowsing_rod.no_valuables"), false)
            }
        }
        context.stack.damage(1, context.player
        ) { player: PlayerEntity? -> player!!.sendToolBreakStatus(player.activeHand) }
        return super.useOnBlock(context)
    }

    private fun spawnFoundParticles(pContext: ItemUsageContext, positionClicked: BlockPos) {
        for (i in 0..359) {
            if (i and 20 == 0) {
                pContext.world.addParticle(ModParticles.CITRINE_PARTICLE,
                        positionClicked.x + 0.5, (positionClicked.y + 1).toDouble(), positionClicked.z + 0.5,
                        Math.cos(i.toDouble()) * 0.25, 0.15, Math.sin(i.toDouble()) * 0.25)
            }
        }
    }

    private fun addNbtToDataTable(player: PlayerEntity?, pos: BlockPos, blockBelow: Block) {
        val dataTablet = player!!.inventory.getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET))
        val nbtData = NbtCompound()
        nbtData.putString("nylemod.last_ore", "Found " + blockBelow.asItem().name.string + " at (" +
                pos.x + ", " + pos.y + ", " + pos.z + ")")
        dataTablet.nbt = nbtData
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.nylemod.dowsing_rod.tooltip.shift"))
        } else {
            tooltip.add(Text.translatable("item.nylemod.dowsing_rod.tooltip"))
        }
    }

    private fun outputValuableCoordinates(blockPos: BlockPos, player: PlayerEntity?, blockBelow: Block) {
        player!!.sendMessage(Text.literal("Found " + blockBelow.asItem().name.string + " at " +
                "(" + blockPos.x + ", " + blockPos.y + ", " + blockPos.z + ")"), false)
    }

    private fun isValuableBlock(state: BlockState): Boolean {
        return state.isIn(ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS)
    }
}