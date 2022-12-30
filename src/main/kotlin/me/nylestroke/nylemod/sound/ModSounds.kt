package me.nylestroke.nylemod.sound

import me.nylestroke.nylemod.NylemodExample
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModSounds {
    var DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore")
    private var MYTHRIL_LAMP_BREAK = registerSoundEvent("mythril_lamp_break")
    private var MYTHRIL_LAMP_STEP = registerSoundEvent("mythril_lamp_step")
    private var MYTHRIL_LAMP_PLACE = registerSoundEvent("mythril_lamp_place")
    private var MYTHRIL_LAMP_HIT = registerSoundEvent("mythril_lamp_hit")
    private var MYTHRIL_LAMP_FALL = registerSoundEvent("mythril_lamp_fall")
    var BAR_BRAWL = registerSoundEvent("bar_brawl")
    var HARD_BASS = registerSoundEvent("hard_bass")
    val MYTHRIL_SOUNDS: BlockSoundGroup = BlockSoundGroup(1f, 1f,
            MYTHRIL_LAMP_BREAK, MYTHRIL_LAMP_STEP, MYTHRIL_LAMP_PLACE,
            MYTHRIL_LAMP_HIT, MYTHRIL_LAMP_FALL)

    private fun registerSoundEvent(name: String): SoundEvent {
        val id = Identifier(NylemodExample.MOD_ID, name)
        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent(id))
    }
}