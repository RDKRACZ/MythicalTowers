package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import kotlin.random.Random

/**
 * Maximum amount of times the cloning ability can be used
 */
private const val MAX_CLONE_ABILITY_USES = 3

/**
 * The amount of clones spawned on every use of the ability
 */
private const val CLONE_AMOUNT = 2

/**
 * The amount of game ticks that must pass before the clone ability can be used
 */
private const val CLONE_ABILITY_COOLDOWN = 100

// NBT

private const val NBT_CLONE_ABILITY_USES = "cloneAbilityUses"
private const val NBT_CLONE_ABILITY_COOLDOWN_STATE = "cloneAbilityCooldownState"
private const val NBT_IS_CLONE = "isClone"

class BlueMeleeRobotEntity(type: EntityType<BlueMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * The current amount of times the clone ability has been used
     */
    private var cloneAbilityUses = 0

    /**
     * The state of the delay counter
     */
    private var cloneAbilityCooldownState = 0

    /**
     * Is this a clone.
     *
     * Clones cannot make clones in order to prevent an infinite loop.
     */
    private var isClone = false

    constructor(world: World) : this(EntityRegistry.BLUE_MELEE_ROBOT, world)

    override fun tick() {
        super.tick()

        if (!isClone) ++cloneAbilityCooldownState
    }

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        // Check all of the conditions
        if (cloneAbilityCooldownState >= CLONE_ABILITY_COOLDOWN && !isClone && cloneAbilityUses <= MAX_CLONE_ABILITY_USES) {
            cloneAbilityCooldownState = 0

            // Summon the clones
            for (i in 0..CLONE_AMOUNT) {
                val clone = BlueMeleeRobotEntity(world)
                // Mark as clone to avoid infinite loop of clones
                clone.isClone = true
                // Spawn nearby
                clone.setPosition(pos.x + (Random.nextInt(3)), pos.y + (Random.nextInt(3)), pos.z + (Random.nextInt(3)))

                world.spawnEntity(clone)
            }

            // Count the use
            ++cloneAbilityUses
        }
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        cloneAbilityUses = nbt.getInt(NBT_CLONE_ABILITY_USES)
        cloneAbilityCooldownState = nbt.getInt(NBT_CLONE_ABILITY_COOLDOWN_STATE)
        isClone = nbt.getBoolean(NBT_IS_CLONE)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_CLONE_ABILITY_USES, cloneAbilityUses)
        nbt.putInt(NBT_CLONE_ABILITY_COOLDOWN_STATE, cloneAbilityCooldownState)
        nbt.putBoolean(NBT_IS_CLONE, isClone)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 80.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 100.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
    }
}
