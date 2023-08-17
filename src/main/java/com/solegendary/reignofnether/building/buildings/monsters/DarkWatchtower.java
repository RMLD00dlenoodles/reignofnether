package com.solegendary.reignofnether.building.buildings.monsters;

import com.solegendary.reignofnether.building.*;
import com.solegendary.reignofnether.hud.AbilityButton;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;

import static com.solegendary.reignofnether.building.BuildingUtils.getAbsoluteBlockData;

public class DarkWatchtower extends Building implements Garrisonable {

    public final static String buildingName = "Dark Watchtower";
    public final static String structureName = "dark_watchtower";
    public final static ResourceCost cost = ResourceCosts.DARK_WATCHTOWER;

    public DarkWatchtower(Level level, BlockPos originPos, Rotation rotation, String ownerName) {
        super(level, originPos, rotation, ownerName, getAbsoluteBlockData(getRelativeBlockData(level), level, originPos, rotation), false);
        this.name = buildingName;
        this.ownerName = ownerName;
        this.portraitBlock = Blocks.DEEPSLATE_BRICKS;
        this.icon = new ResourceLocation("minecraft", "textures/block/deepslate_bricks.png");

        this.foodCost = cost.food;
        this.woodCost = cost.wood;
        this.oreCost = cost.ore;
        this.popSupply = cost.population;
        this.buildTimeModifier = 0.8f;

        this.startingBlockTypes.add(Blocks.DEEPSLATE_BRICKS);
        this.startingBlockTypes.add(Blocks.DEEPSLATE_BRICK_SLAB);
        this.startingBlockTypes.add(Blocks.CRACKED_DEEPSLATE_BRICKS);
    }

    public static ArrayList<BuildingBlock> getRelativeBlockData(LevelAccessor level) {
        return BuildingBlockData.getBuildingBlocks(structureName, level);
    }

    public static AbilityButton getBuildButton(Keybinding hotkey) {
        return new AbilityButton(
            DarkWatchtower.buildingName,
            new ResourceLocation("minecraft", "textures/block/deepslate_bricks.png"),
            hotkey,
            () -> BuildingClientEvents.getBuildingToPlace() == DarkWatchtower.class,
            () -> false,
            () -> true,
            () -> BuildingClientEvents.setBuildingToPlace(DarkWatchtower.class),
            null,
            List.of(
                    FormattedCharSequence.forward(DarkWatchtower.buildingName, Style.EMPTY.withBold(true)),
                    ResourceCosts.getFormattedCost(cost),
                    ResourceCosts.getFormattedPop(cost),
                    FormattedCharSequence.forward("", Style.EMPTY),
                    FormattedCharSequence.forward("An ominous tower that can garrison units.", Style.EMPTY),
                    FormattedCharSequence.forward("Garrisoned ranged units attack with increased range.", Style.EMPTY)
            ),
            null
        );
    }

    @Override
    public BlockPos getEntryPosition() {
        return new BlockPos(2,11,2);
    }

    @Override
    public BlockPos getExitPosition() {
        return new BlockPos(2,1,2);
    }
}