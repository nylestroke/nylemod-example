package me.nylestroke.nylemod.util

import net.minecraft.nbt.NbtCompound

interface IEntityDataSaver {
    fun getPersistentData(): NbtCompound
}