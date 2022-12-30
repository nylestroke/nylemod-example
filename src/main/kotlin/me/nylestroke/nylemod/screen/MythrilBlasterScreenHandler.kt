package me.nylestroke.nylemod.screen

import me.nylestroke.nylemod.screen.slot.ModFuelSlot
import me.nylestroke.nylemod.screen.slot.ModResultSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class MythrilBlasterScreenHandler @JvmOverloads constructor(syncId: Int, playerInventory: PlayerInventory,
                                                            inventory: Inventory = SimpleInventory(4), delegate: PropertyDelegate = ArrayPropertyDelegate(4)
) : ScreenHandler(ModScreenHandlers.MYTHRIL_BLASTER_SCREEN_HANDLER, syncId) {
    private val inventory: Inventory
    private val propertyDelegate: PropertyDelegate

    init {
        ScreenHandler.checkSize(inventory, 4)
        this.inventory = inventory
        inventory.onOpen(playerInventory.player)
        propertyDelegate = delegate

        //
        this.addSlot(ModFuelSlot(inventory, 0, 18, 50))
        this.addSlot(Slot(inventory, 1, 66, 16))
        this.addSlot(Slot(inventory, 2, 66, 50))
        this.addSlot(ModResultSlot(inventory, 3, 114, 33))
        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)
        addProperties(delegate)
    }

    val isCrafting: Boolean
        get() = propertyDelegate[0] > 0

    fun hasFuel(): Boolean {
        return propertyDelegate[2] > 0
    }

    val scaledProgress: Int
        get() {
            val progress = propertyDelegate[0]
            val maxProgress = propertyDelegate[1] // Max Progress
            val progressArrowSize = 26 // This is the width in pixels of your arrow
            return if (maxProgress != 0 && progress != 0) progress * progressArrowSize / maxProgress else 0
        }
    val scaledFuelProgress: Int
        get() {
            val fuelProgress = propertyDelegate[2]
            val maxFuelProgress = propertyDelegate[3]
            val fuelProgressSize = 14
            return if (maxFuelProgress != 0) (fuelProgress.toFloat() / maxFuelProgress.toFloat() * fuelProgressSize).toInt() else 0
        }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot: Slot = this.slots.get(invSlot)
        if (slot != null && slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (invSlot < inventory.size()) {
                if (!this.insertItem(originalStack, inventory.size(), this.slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }
            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }
        return newStack
    }

    private fun addPlayerInventory(playerInventory: PlayerInventory) {
        for (i in 0..2) {
            for (l in 0..8) {
                this.addSlot(Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(playerInventory: PlayerInventory) {
        for (i in 0..8) {
            this.addSlot(Slot(playerInventory, i, 8 + i * 18, 144))
        }
    }
}