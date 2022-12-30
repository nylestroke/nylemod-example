package me.nylestroke.nylemod.util

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object InventoryUtil {
    fun hasPlayerStackInInventory(player: PlayerEntity?, item: Item?): Boolean {
        for (i in 0 until player!!.inventory.size()) {
            val currentStack = player.inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isItemEqual(ItemStack(item))) {
                return true
            }
        }
        return false
    }

    fun getFirstInventoryIndex(player: PlayerEntity?, item: Item?): Int {
        for (i in 0 until player!!.inventory.size()) {
            val currentStack = player.inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isItemEqual(ItemStack(item))) {
                return i
            }
        }
        return -1
    }
}