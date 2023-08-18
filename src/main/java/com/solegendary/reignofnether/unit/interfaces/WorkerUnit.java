package com.solegendary.reignofnether.unit.interfaces;

import com.solegendary.reignofnether.resources.ResourceSources;
import com.solegendary.reignofnether.unit.goals.BuildRepairGoal;
import com.solegendary.reignofnether.unit.goals.GatherResourcesGoal;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public interface WorkerUnit {

    // damage animals should always take from workers
    int DAMAGE_TO_ANIMALS = 3;

    public BuildRepairGoal getBuildRepairGoal();
    public GatherResourcesGoal getGatherResourceGoal();
    public BlockState getReplantBlockState();

    public static void tick(WorkerUnit unit) {
        BuildRepairGoal buildRepairGoal = unit.getBuildRepairGoal();
        if (buildRepairGoal != null)
            buildRepairGoal.tick();
        GatherResourcesGoal gatherResourcesGoal = unit.getGatherResourceGoal();
        if (gatherResourcesGoal != null)
            gatherResourcesGoal.tick();

        LivingEntity entity = (LivingEntity) unit;
        ItemStack mainHandItem = entity.getItemBySlot(EquipmentSlot.MAINHAND);

        if (unit.getBuildRepairGoal().isBuilding() &&
                !mainHandItem.is(Items.IRON_SHOVEL)) {
            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
        }
        else if (unit.getGatherResourceGoal().isGathering()) {
            switch (unit.getGatherResourceGoal().getTargetResourceName()) {
                case FOOD -> {
                    if (!mainHandItem.is(Items.IRON_HOE))
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_HOE));
                }
                case WOOD -> {
                    if (!mainHandItem.is(Items.IRON_AXE))
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                }
                case ORE -> {
                    if (!mainHandItem.is(Items.IRON_PICKAXE))
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
                }
                case NONE -> {
                    if (!mainHandItem.is(Items.AIR))
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));
                }
            }
        } else if (entity instanceof AttackerUnit attackerUnit &&
                ResourceSources.isHuntableAnimal(((Unit) entity).getTargetGoal().getTarget())) {
            if (!mainHandItem.is(Items.STONE_SWORD))
                entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
        }
        else {
            if (!mainHandItem.is(Items.AIR))
                entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));
        }
    }

    public static void resetBehaviours(WorkerUnit unit) {
        unit.getBuildRepairGoal().stopBuilding();
        unit.getGatherResourceGoal().stopGathering();
    }

    // only properly works serverside - clientside requires packet updates
    public static boolean isIdle(WorkerUnit unit) {
        GatherResourcesGoal resGoal = unit.getGatherResourceGoal();

        boolean isMoving = !((PathfinderMob) unit).getNavigation().isDone();
        boolean isGathering = resGoal.isGathering();
        boolean isGatheringIdle = resGoal.isIdle();
        boolean isBuilding = unit.getBuildRepairGoal().getBuildingTarget() != null;
        boolean isFarming = resGoal.isFarming();

        return !isMoving && !isGathering && !isBuilding && isGatheringIdle && !isFarming;
    }
}
