package me.nylestroke.nylemod.block.entity

import me.nylestroke.nylemod.mixin.SignTypeAccessor
import net.minecraft.util.SignType

object ModSignTypes {
    val JACARANDA: SignType = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("jacaranda"))
}