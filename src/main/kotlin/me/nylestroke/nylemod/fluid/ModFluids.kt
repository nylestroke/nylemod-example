package me.nylestroke.nylemod.fluid

import me.nylestroke.nylemod.NylemodExample
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModFluids {
    val HONEY_STILL: FlowableFluid = register("honey_still", HoneyFluid.Still())
    val HONEY_FLOWING: FlowableFluid = register("honey_flowing", HoneyFluid.Flowing())
    private fun register(name: String, flowableFluid: FlowableFluid): FlowableFluid {
        return Registry.register<Fluid, FlowableFluid>(Registry.FLUID, Identifier(NylemodExample.MOD_ID, name), flowableFluid)
    }
}