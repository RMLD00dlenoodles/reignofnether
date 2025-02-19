package com.solegendary.reignofnether.survival;

import com.solegendary.reignofnether.building.buildings.piglins.Portal;
import com.solegendary.reignofnether.registrars.EntityRegistrar;
import com.solegendary.reignofnether.survival.spawners.PiglinWaveSpawner;
import com.solegendary.reignofnether.unit.UnitServerEvents;
import com.solegendary.reignofnether.unit.interfaces.Unit;
import com.solegendary.reignofnether.unit.units.piglins.HoglinUnit;
import com.solegendary.reignofnether.unit.units.villagers.RavagerUnit;
import com.solegendary.reignofnether.util.Faction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.List;
import java.util.Random;

import static com.solegendary.reignofnether.survival.SurvivalServerEvents.ENEMY_OWNER_NAME;
import static com.solegendary.reignofnether.survival.spawners.WaveSpawner.getModifiedPopCost;

public class WavePortal {

    private static final int SPAWN_TICKS_MAX = 600;
    private static int spawnTicks = 0;

    public final Portal portal;
    public final Wave wave;
    private int initialSpawnPop;

    private BlockPos lastOnPos;

    public WavePortal(Portal portal, Wave wave) {
        this.portal = portal;
        this.portal.selfBuilding = true;
        this.wave = wave;
        this.initialSpawnPop = (wave.population / wave.getNumPortals()) / 2;
    }

    public Portal getPortal() {
        return portal;
    }

    public void tick(long ticksToAdd) {
        if (!portal.isBuilt)
            return;

        if (initialSpawnPop > 0) {
            doSpawn();
        } else {
            int pop = SurvivalServerEvents.getTotalEnemyPopulation();
            if (pop > wave.population * 2.0f) {
                return;
            }
            else if (spawnTicks >= SPAWN_TICKS_MAX) {
                spawnTicks = 0;
                doSpawn();
            } else {
                spawnTicks += ticksToAdd;
            }
        }
    }

    public void doSpawn() {
        Random random = new Random();
        int tier = random.nextInt(wave.highestUnitTier) + 1;
        EntityType<? extends Unit> mobType = (EntityType<? extends Unit>) PiglinWaveSpawner.getRandomUnitOfTier(tier);

        ServerLevel level = (ServerLevel) portal.getLevel();

        // produceUnit spawns them before applying the ownerName, meaning they aren't registered as WaveEnemies automatically
        Entity entity = portal.produceUnit(level, mobType, ENEMY_OWNER_NAME, true);

        if (entity instanceof Unit unit) {

            if (wave.highestUnitTier >= 3 && entity instanceof HoglinUnit hoglinUnit) {
                Entity entityPassenger = UnitServerEvents.spawnMob(EntityRegistrar.HEADHUNTER_UNIT.get(),
                        level, hoglinUnit.getOnPos(), ENEMY_OWNER_NAME);
                if (entityPassenger instanceof Unit pUnit) {
                    entityPassenger.startRiding(hoglinUnit);
                    if (initialSpawnPop > 0)
                        initialSpawnPop -= getModifiedPopCost(unit);
                }
            }

            List<Unit> enemies = SurvivalServerEvents.getCurrentEnemies().stream().map(e -> e.unit).toList();
            if (!enemies.contains(unit))
                SurvivalServerEvents.getCurrentEnemies().add(new WaveEnemy(unit));

            if (initialSpawnPop > 0)
                initialSpawnPop -= getModifiedPopCost(unit);
        }
    }
}
