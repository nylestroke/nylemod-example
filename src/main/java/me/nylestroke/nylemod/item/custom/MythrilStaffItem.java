package me.nylestroke.nylemod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MythrilStaffItem extends Item {

    public MythrilStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient()) {

            double posX = user.prevX;
            double posY = user.prevY;
            double posZ = user.prevZ;

            world.createExplosion(user, posX, posY, posZ, 1f, Explosion.DestructionType.DESTROY);
        }

        return super.use(world, user, hand);
    }
}
