package me.nylestroke.nylemod.entity.custom

import me.nylestroke.nylemod.entity.ModEntities
import me.nylestroke.nylemod.entity.variant.RaccoonVariant
import me.nylestroke.nylemod.item.ModItems
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.scoreboard.AbstractTeam
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

open class RaccoonEntity(entityType: EntityType<out TameableEntity?>, world: World) : TameableEntity(entityType, world), IAnimatable {
    private val factory = AnimationFactory(this)
    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        val baby: RaccoonEntity? = ModEntities.RACCOON.create(world)
        val variant: RaccoonVariant = Util.getRandom<RaccoonVariant>(RaccoonVariant.values(), this.random)
        baby?.variant = variant
        return baby
    }

    override fun isBreedingItem(stack: ItemStack): Boolean {
        return stack.item === ModItems.GRAPE
    }

    protected override fun initGoals() {
        this.goalSelector.add(0, SwimGoal(this))
        this.goalSelector.add(1, SitGoal(this))
        this.goalSelector.add(2, WanderAroundPointOfInterestGoal(this, 0.75, false))
        this.goalSelector.add(3, WanderAroundFarGoal(this, 0.75, 1f))
        this.goalSelector.add(4, LookAroundGoal(this))
        this.goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        this.targetSelector.add(1, AnimalMateGoal(this, 1.0))
    }

    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(AnimationBuilder().addAnimation("animation.raccoon.walk", true))
            return PlayState.CONTINUE
        }
        if (isSitting) {
            event.controller.setAnimation(AnimationBuilder().addAnimation("animation.raccoon.sitting", true))
            return PlayState.CONTINUE
        }
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.raccoon.idle", true))
        return PlayState.CONTINUE
    }

    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController<IAnimatable>(this, "controller",
                0f, AnimationController.IAnimationPredicate { event: AnimationEvent<*> -> predicate(event) }))
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.ENTITY_DOLPHIN_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent? {
        return SoundEvents.ENTITY_DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.ENTITY_PIG_DEATH
    }

    override fun playStepSound(pos: BlockPos, state: BlockState) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f)
    }

    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val itemstack = player.getStackInHand(hand)
        val item = itemstack.item
        val itemForTaming = Items.APPLE
        if (isBreedingItem(itemstack)) {
            return super.interactMob(player, hand)
        }
        if (item === itemForTaming && !isTamed()) {
            return if (this.world.isClient()) {
                ActionResult.CONSUME
            } else {
                if (!player.abilities.creativeMode) {
                    itemstack.decrement(1)
                }
                if (!this.world.isClient()) {
                    super.setOwner(player)
                    this.navigation.recalculatePath()
                    this.target = null
                    this.world.sendEntityStatus(this, 7.toByte())
                    setSit(true)
                }
                ActionResult.SUCCESS
            }
        }
        if (isTamed && !this.world.isClient() && hand == Hand.MAIN_HAND) {
            setSit(!isSitting)
            return ActionResult.SUCCESS
        }
        return if (itemstack.item === itemForTaming) {
            ActionResult.PASS
        } else super.interactMob(player, hand)
    }

    private fun setSit(sitting: Boolean) {
        this.dataTracker.set<Boolean>(SITTING, sitting)
        super.setSitting(sitting)
    }

    override fun isSitting(): Boolean {
        return this.dataTracker.get<Boolean>(SITTING)
    }

    override fun setTamed(tamed: Boolean) {
        super.setTamed(tamed)
        if (tamed) {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 60.0
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)?.baseValue = 4.0
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.5
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 30.0
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)?.baseValue = 2.0
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.25
        }
    }

    override fun getScoreboardTeam(): AbstractTeam? {
        return super.getScoreboardTeam()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putBoolean("isSitting", this.dataTracker.get<Boolean>(SITTING))
        nbt.putInt("Variant", typeVariant)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.dataTracker.set<Boolean>(SITTING, nbt.getBoolean("isSitting"))
        this.dataTracker.set<Int>(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"))
    }

    protected override fun initDataTracker() {
        super.initDataTracker()
        this.dataTracker.startTracking<Boolean>(SITTING, false)
        this.dataTracker.startTracking<Int>(DATA_ID_TYPE_VARIANT, 0)
    }

    override fun canBeLeashedBy(player: PlayerEntity): Boolean {
        return false
    }

    override fun initialize(world: ServerWorldAccess, difficulty: LocalDifficulty?,
                            spawnReason: SpawnReason?, entityData: EntityData?,
                            entityNbt: NbtCompound?): EntityData? {
        val variant: RaccoonVariant = Util.getRandom<RaccoonVariant>(RaccoonVariant.values(), this.random)
        this.variant = variant
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    var variant: RaccoonVariant
        get() = RaccoonVariant.Companion.byId(typeVariant and 255)
        private set(variant) {
            this.dataTracker.set<Int>(DATA_ID_TYPE_VARIANT, variant.id and 255)
        }
    private val typeVariant: Int
        private get() = this.dataTracker.get<Int>(DATA_ID_TYPE_VARIANT)

    companion object {
        fun setAttributes(): DefaultAttributeContainer.Builder {
            return AnimalEntity.createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                    .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
        }

        /* TAMEABLE ENTITY */
        private val SITTING: TrackedData<Boolean> = DataTracker.registerData<Boolean>(RaccoonEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        /* VARIANTS */
        private val DATA_ID_TYPE_VARIANT: TrackedData<Int> = DataTracker.registerData<Int>(RaccoonEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
    }
}