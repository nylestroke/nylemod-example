package me.nylestroke.nylemod.item;

import me.nylestroke.nylemod.NylemodExample;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static ItemGroup MYTHRIL = FabricItemGroupBuilder.build(new Identifier(NylemodExample.MOD_ID, "mythril"),
            () -> new ItemStack(ModItems.MYTHRIL_INGOT));

}
