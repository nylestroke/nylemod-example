package me.nylestroke.nylemod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedyBlock extends Block {

    public SpeedyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (world.isClient()) {
            if (hand == Hand.MAIN_HAND) {
                player.sendMessage(new LiteralText("CLIENT: This is THE CLIENT! MAIN HAND!"), false);
            } else {
                player.sendMessage(new LiteralText("CLIENT: This is THE CLIENT! OFF HAND!"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("item.nylemod.speedy_block.tooltip"));
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

        if (!world.isClient()) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = ((LivingEntity) entity);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300));
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
