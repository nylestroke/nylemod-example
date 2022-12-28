package me.nylestroke.nylemod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.example.entity.RocketProjectile;

public class MythrilStaffItem extends Item {

    public MythrilStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        player.getItemCooldownManager().set(this, 8);

        if (!world.isClient()) {
            RocketProjectile arrowEntity = new RocketProjectile(world, player);
            arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F,
                    1.0F * 3.0F, 1.0F);

            arrowEntity.setDamage(2.5);
            arrowEntity.age = 35;
            arrowEntity.hasNoGravity();

            world.spawnEntity(arrowEntity);

        }

        return super.use(world, player, hand);
    }
}
