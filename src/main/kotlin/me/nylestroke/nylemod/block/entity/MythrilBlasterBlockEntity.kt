package me.nylestroke.nylemod.block.entity

import me.nylestroke.nylemod.recipe.custom.MythrilBlasterRecipe
import me.nylestroke.nylemod.screen.MythrilBlasterScreenHandler
import me.nylestroke.nylemod.util.inventory.ImplementedInventory
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

open class MythrilBlasterBlockEntity(pos: BlockPos?, state: BlockState?) : BlockEntity(ModBlockEntities.MYTHRIL_BLASTER, pos, state),
    NamedScreenHandlerFactory, ImplementedInventory {
    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(4, ItemStack.EMPTY)

    override fun markDirty() {
        TODO("Not yet implemented")
    }

    private val propertyDelegate: PropertyDelegate
    private var progress = 0
    private var maxProgress = 72
    private var fuelTime = 0
    private var maxFuelTime = 0

    init {
        propertyDelegate = object : PropertyDelegate {
            override fun get(index: Int): Int {
                return when (index) {
                    0 -> progress
                    1 -> maxProgress
                    2 -> fuelTime
                    3 -> maxFuelTime
                    else -> 0
                }
            }

            override fun set(index: Int, value: Int) {
                when (index) {
                    0 -> progress = value
                    1 -> maxProgress = value
                    2 -> fuelTime = value
                    3 -> maxFuelTime = value
                }
            }

            override fun size(): Int {
                return 4
            }
        }
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return MythrilBlasterScreenHandler(syncId, inv, this, propertyDelegate)
    }

    override fun getDisplayName(): Text {
        return Text.literal("Mythril Blaster")
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        nbt.putInt("blaster.progress", progress)
        nbt.putInt("blaster.fuelTime", fuelTime)
        nbt.putInt("blaster.maxFuelTime", maxFuelTime)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
        progress = nbt.getInt("blaster.progress")
        fuelTime = nbt.getInt("blaster.fuelTime")
        maxFuelTime = nbt.getInt("blaster.maxFuelTime")
    }

    private fun consumeFuel() {
        if (!getStack(0).isEmpty) {
            fuelTime = FuelRegistry.INSTANCE[this.removeStack(0, 1).item]
            maxFuelTime = fuelTime
        }
    }

    private fun resetProgress() {
        progress = 0
    }

    companion object {
        fun tick(world: World?, pos: BlockPos?, state: BlockState?, entity: MythrilBlasterBlockEntity) {
            if (isConsumingFuel(entity)) {
                entity.fuelTime--
            }
            if (hasRecipe(entity)) {
                if (hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                    entity.consumeFuel()
                }
                if (isConsumingFuel(entity)) {
                    entity.progress++
                    if (entity.progress > entity.maxProgress) {
                        craftItem(entity)
                    }
                }
            } else {
                entity.resetProgress()
            }
        }

        private fun hasFuelInFuelSlot(entity: MythrilBlasterBlockEntity): Boolean {
            return !entity.getStack(0).isEmpty
        }

        private fun isConsumingFuel(entity: MythrilBlasterBlockEntity): Boolean {
            return entity.fuelTime > 0
        }

        private fun hasRecipe(entity: MythrilBlasterBlockEntity): Boolean {
            val world: World? = entity.world
            val inventory = SimpleInventory(entity.items.size)
            for (i in entity.items.indices) {
                inventory.setStack(i, entity.getStack(i))
            }
            val match: Optional<MythrilBlasterRecipe?>? = world?.recipeManager
                ?.getFirstMatch(MythrilBlasterRecipe.Type.INSTANCE, inventory, world)
            return (match!!.isPresent && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match?.get()!!.output))
        }

        private fun craftItem(entity: MythrilBlasterBlockEntity) {
            val world: World? = entity.world
            val inventory = SimpleInventory(entity.items.size)
            for (i in entity.items.indices) {
                inventory.setStack(i, entity.getStack(i))
            }
            val match: Optional<MythrilBlasterRecipe?>? = world?.recipeManager
                    ?.getFirstMatch(MythrilBlasterRecipe.Type.INSTANCE, inventory, world)
            if (match!!.isPresent) {
                entity.removeStack(1, 1)
                entity.removeStack(2, 1)
                entity.setStack(3, ItemStack(match.get().output.item,
                        entity.getStack(3).count + 1))
                entity.resetProgress()
            }
        }

        private fun canInsertItemIntoOutputSlot(inventory: SimpleInventory, output: ItemStack): Boolean {
            return inventory.getStack(3).item === output.item || inventory.getStack(3).isEmpty
        }

        private fun canInsertAmountIntoOutputSlot(inventory: SimpleInventory): Boolean {
            return inventory.getStack(3).maxCount > inventory.getStack(3).count
        }
    }
}