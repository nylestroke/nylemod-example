package me.nylestroke.nylemod.block.custom

import me.nylestroke.nylemod.item.ModItems
import net.minecraft.block.CropBlock
import net.minecraft.item.ItemConvertible

open class GrapeVineBlock(settings: Settings) : CropBlock(settings) {

    override fun getSeedsItem(): ItemConvertible {
        return ModItems.GRAPE_SEEDS
    }
}
