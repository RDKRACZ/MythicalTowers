package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers
import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.blockSettings
import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.block.building.CustomBricksBlock
import com.redgrapefruit.mythicaltowers.common.block.building.PureMaterialBlock
import com.redgrapefruit.mythicaltowers.common.block.trap.FireStationBlock
import com.redgrapefruit.mythicaltowers.common.block.trap.GreenTntBlock
import com.redgrapefruit.mythicaltowers.common.block.trap.JumpPadBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

/**
 * Stores and registers mod's blocks
 */
@Suppress("MemberVisibilityCanBePrivate")
object BlockRegistry {

    // Building
    val PURE_GREEN_BLOCK = PureMaterialBlock(blockSettings(3.0f))
    val PURE_YELLOW_BLOCK = PureMaterialBlock(blockSettings(4.5f))
    val PURE_ORANGE_BLOCK = PureMaterialBlock(blockSettings(6.0f))
    val PURE_RED_BLOCK = PureMaterialBlock(blockSettings(7.5f))
    val PURE_BLUE_BLOCK = PureMaterialBlock(blockSettings(9.0f))
    val PURE_PURPLE_BLOCK = PureMaterialBlock(blockSettings(10.5f))
    val PURE_GRAY_BLOCK = PureMaterialBlock(blockSettings(12.0f))
    val PURE_BLACK_BLOCK = PureMaterialBlock(blockSettings(13.5f))

    val GREEN_BRICKS = CustomBricksBlock(blockSettings(3.5f))
    val YELLOW_BRICKS = CustomBricksBlock(blockSettings(5f))
    val ORANGE_BRICKS = CustomBricksBlock(blockSettings(7.5f))
    val RED_BRICKS = CustomBricksBlock(blockSettings(9f))
    val BLUE_BRICKS = CustomBricksBlock(blockSettings(10.5f))
    val PURPLE_BRICKS = CustomBricksBlock(blockSettings(12.0f))
    val GRAY_BRICKS = CustomBricksBlock(blockSettings(13.5f))
    val BLACK_BRICKS = CustomBricksBlock(blockSettings(15.0f))

    // Traps
    val GREEN_TNT = GreenTntBlock()

    val GREEN_JUMP_PAD = JumpPadBlock(1.5, 1.1)

    val GREEN_FIRE_STATION = FireStationBlock(blockSettings(2.0f))

    fun init() {
        register("pure_green_block", PURE_GREEN_BLOCK)
        register("pure_yellow_block", PURE_YELLOW_BLOCK)
        register("pure_orange_block", PURE_ORANGE_BLOCK)
        register("pure_red_block", PURE_RED_BLOCK)
        register("pure_blue_block", PURE_BLUE_BLOCK)
        register("pure_purple_block", PURE_PURPLE_BLOCK)
        register("pure_gray_block", PURE_GRAY_BLOCK)
        register("pure_black_block", PURE_BLACK_BLOCK)

        register("green_bricks", GREEN_BRICKS)
        register("yellow_bricks", YELLOW_BRICKS,)
        register("orange_bricks", ORANGE_BRICKS)
        register("red_bricks", RED_BRICKS)
        register("blue_bricks", BLUE_BRICKS)
        register("purple_bricks", PURPLE_BRICKS)
        register("gray_bricks", GRAY_BRICKS)
        register("black_bricks", BLACK_BRICKS)

        register("green_tnt", GREEN_TNT)

        register("green_jump_pad", GREEN_JUMP_PAD)

        register("green_fire_station", GREEN_FIRE_STATION)
    }

    /**
     * Registers a block and a [BlockItem]
     *
     * @param name  Block name
     * @param block Block instance
     */
    private fun register(name: String, block: Block) {
        Registry.register(Registry.BLOCK, idOf(name), block)
        Registry.register(Registry.ITEM, idOf(name), BlockItem(block, Item.Settings().group(MythicalTowers.GROUP)))
    }
}