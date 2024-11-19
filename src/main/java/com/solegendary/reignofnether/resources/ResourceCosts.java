package com.solegendary.reignofnether.resources;

import com.solegendary.reignofnether.config.ReignOfNetherCommonConfigs;
import com.solegendary.reignofnether.util.MyRenderer;
import net.minecraft.util.FormattedCharSequence;

// defined here because we need to be able to access in both
// static (for ProductionItems) and nonstatic (for getCurrentPopulation) contexts
// and we can't declare static getters in the Unit interface
public class ResourceCosts {

    public static ResourceCost ZOMBIE_VILLAGER;
    public static ResourceCost CREEPER;
    public static ResourceCost ZOMBIE;
    public static ResourceCost SKELETON;
    public static ResourceCost STRAY;
    public static ResourceCost HUSK;
    public static ResourceCost DROWNED;
    public static ResourceCost SPIDER;
    public static ResourceCost POISON_SPIDER;
    public static ResourceCost WARDEN;
    public static ResourceCost ZOMBIE_PIGLIN;
    public static ResourceCost ZOGLIN;
    public static ResourceCost VILLAGER;
    public static ResourceCost IRON_GOLEM;
    public static ResourceCost PILLAGER;
    public static ResourceCost VINDICATOR;
    public static ResourceCost WITCH;
    public static ResourceCost EVOKER;
    public static ResourceCost RAVAGER;
    public static ResourceCost GRUNT;
    public static ResourceCost BRUTE;
    public static ResourceCost HEADHUNTER;
    public static ResourceCost HOGLIN;
    public static ResourceCost BLAZE;
    public static ResourceCost WITHER_SKELETON;
    public static ResourceCost GHAST;

    //BUILDINGS

    public static ResourceCost STOCKPILE;
    public static ResourceCost OAK_BRIDGE;
    public static ResourceCost SPRUCE_BRIDGE;
    public static ResourceCost BLACKSTONE_BRIDGE;
    //Monster
    public static ResourceCost MAUSOLEUM;
    public static ResourceCost HAUNTED_HOUSE;
    public static ResourceCost PUMPKIN_FARM;
    public static ResourceCost SCULK_CATALYST;
    public static ResourceCost GRAVEYARD;
    public static ResourceCost SPIDER_LAIR;
    public static ResourceCost DUNGEON;
    public static ResourceCost LABORATORY;
    public static ResourceCost DARK_WATCHTOWER;
    public static ResourceCost STRONGHOLD;
    //Villagers
    public static ResourceCost TOWN_CENTRE;
    public static ResourceCost VILLAGER_HOUSE;
    public static ResourceCost WHEAT_FARM;
    public static ResourceCost BARRACKS;
    public static ResourceCost BLACKSMITH;
    public static ResourceCost ARCANE_TOWER;
    public static ResourceCost LIBRARY;
    public static ResourceCost WATCHTOWER;
    public static ResourceCost CASTLE;
    public static ResourceCost IRON_GOLEM_BUILDING;
    //Piglins
    public static ResourceCost CENTRAL_PORTAL;
    public static ResourceCost BASIC_PORTAL;
    public static ResourceCost NETHERWART_FARM;
    public static ResourceCost BASTION;
    public static ResourceCost HOGLIN_STABLES;
    public static ResourceCost FLAME_SANCTUARY;
    public static ResourceCost WITHER_SHRINE;
    public static ResourceCost FORTRESS;

    public static FormattedCharSequence getFormattedCost(ResourceCost resCost) {
        String str = "";
        if (resCost.food > 0)
            str += "\uE000  " + resCost.food + "     ";
        if (resCost.wood > 0)
            str += "\uE001  " + resCost.wood + "     ";
        if (resCost.ore > 0)
            str += "\uE002  " + resCost.ore + "     ";
        str = str.trim();
        return FormattedCharSequence.forward(str, MyRenderer.iconStyle);
    }
    public static FormattedCharSequence getFormattedPopAndTime(ResourceCost resCost) {
        return FormattedCharSequence.forward("\uE003  " + resCost.population + "     \uE004  " + resCost.ticks/ResourceCost.TICKS_PER_SECOND + "s", MyRenderer.iconStyle);
    }
    public static FormattedCharSequence getFormattedPop(ResourceCost resCost) {
        return FormattedCharSequence.forward("\uE003  " + resCost.population, MyRenderer.iconStyle);
    }
    public static FormattedCharSequence getFormattedTime(ResourceCost resCost) {
        return FormattedCharSequence.forward("\uE004  " + resCost.ticks/ResourceCost.TICKS_PER_SECOND + "s", MyRenderer.iconStyle);
    }

    public static final int REPLANT_WOOD_COST = 1;
    public static final int REDUCED_REPLANT_WOOD_COST = 0;
    public static final int DEFAULT_MAX_POPULATION = 150;
    /*
        Unit costs are defined here during CommonSetup
        Do not read values and initialize from config earlier, else will result in IllegalStateException !!!
     */
    public static void deferredLoadResourceCosts() {
        //TODO: Attempt to replace manual definitions with iteration through UnitCosts#getDeclaredFields

        // ******************* UNITS ******************* //
        // Monsters
        CREEPER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.CREEPER);
        ZOMBIE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.ZOMBIE);
        ZOMBIE_VILLAGER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.ZOMBIE_VILLAGER);
        SKELETON = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.SKELETON);
        STRAY = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.STRAY);
        HUSK = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.HUSK);
        DROWNED = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.DROWNED);
        SPIDER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.SPIDER);
        POISON_SPIDER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.POISON_SPIDER);
        WARDEN = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.WARDEN);
        ZOMBIE_PIGLIN = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.ZOMBIE_PIGLIN);
        ZOGLIN = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.ZOGLIN);
        // Villagers
        VILLAGER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.VILLAGER);
        IRON_GOLEM = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.IRON_GOLEM);
        PILLAGER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.PILLAGER);
        VINDICATOR = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.VINDICATOR);
        WITCH = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.WITCH);
        EVOKER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.EVOKER);
        RAVAGER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.RAVAGER);
        // Piglins
        GRUNT = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.GRUNT);
        BRUTE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.BRUTE);
        HEADHUNTER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.HEADHUNTER);
        HOGLIN = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.HOGLIN);
        BLAZE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.BLAZE);
        WITHER_SKELETON = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.WITHER_SKELETON);
        GHAST = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.UnitCosts.GHAST);
        // ******************* BUILDINGS ******************* //
        STOCKPILE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.STOCKPILE);
        OAK_BRIDGE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.OAK_BRIDGE);
        SPRUCE_BRIDGE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.SPRUCE_BRIDGE);
        BLACKSTONE_BRIDGE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.BLACKSTONE_BRIDGE);
        // Monsters
        MAUSOLEUM = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.MAUSOLEUM);
        HAUNTED_HOUSE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.HAUNTED_HOUSE);
        PUMPKIN_FARM = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.PUMPKIN_FARM);
        SCULK_CATALYST = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.SCULK_CATALYST);
        GRAVEYARD = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.GRAVEYARD);
        SPIDER_LAIR = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.SPIDER_LAIR);
        DUNGEON = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.DUNGEON);
        LABORATORY = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.LABORATORY);
        DARK_WATCHTOWER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.DARK_WATCHTOWER);
        STRONGHOLD = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.STRONGHOLD);
        // Villagers
        TOWN_CENTRE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.TOWN_CENTRE);
        VILLAGER_HOUSE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.VILLAGER_HOUSE);
        WHEAT_FARM = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.WHEAT_FARM);
        BARRACKS = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.BARRACKS);
        BLACKSMITH = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.BLACKSMITH);
        ARCANE_TOWER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.ARCANE_TOWER);
        LIBRARY = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.LIBRARY);
        WATCHTOWER = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.WATCHTOWER);
        CASTLE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.CASTLE);
        IRON_GOLEM_BUILDING = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.IRON_GOLEM_BUILDING);
        // Piglins
        CENTRAL_PORTAL = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.CENTRAL_PORTAL);
        BASIC_PORTAL= ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.BASIC_PORTAL);
        NETHERWART_FARM = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.NETHERWART_FARM);
        BASTION = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.BASTION);
        HOGLIN_STABLES = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.HOGLIN_STABLES);
        FLAME_SANCTUARY = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.FLAME_SANCTUARY);
        WITHER_SHRINE = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.WITHER_SHRINE);
        FORTRESS = ResourceCost.fromConfigEntry(ReignOfNetherCommonConfigs.BuildingCosts.FORTRESS);

    }

    // ******************* RESEARCH ******************* //
    public static ResourceCost RESEARCH_GOLEM_SMITHING = ResourceCost.Research(0, 150,200, 90);
    public static ResourceCost RESEARCH_LAB_LIGHTNING_ROD = ResourceCost.Research(0,0,400, 120);
    public static ResourceCost RESEARCH_RESOURCE_CAPACITY = ResourceCost.Research(200,200,0, 90);
    public static ResourceCost RESEARCH_SPIDER_JOCKEYS = ResourceCost.Research(300,250,0, 100);
    public static ResourceCost RESEARCH_SPIDER_WEBS = ResourceCost.Research(0, 300, 300, 140);
    public static ResourceCost RESEARCH_POISON_SPIDERS = ResourceCost.Research(400,0,250, 150);
    public static ResourceCost RESEARCH_HUSKS = ResourceCost.Research(500,0,500, 200);
    public static ResourceCost RESEARCH_DROWNED = ResourceCost.Research(500,0,500, 200);
    public static ResourceCost RESEARCH_STRAYS = ResourceCost.Research(500,500,0, 200);
    public static ResourceCost RESEARCH_LINGERING_POTIONS = ResourceCost.Research(250,250,250, 140);
    public static ResourceCost RESEARCH_EVOKER_VEXES = ResourceCost.Research(500,0,300, 120);
    public static ResourceCost RESEARCH_CASTLE_FLAG = ResourceCost.Research(200,150,150, 90);
    public static ResourceCost RESEARCH_GRAND_LIBRARY = ResourceCost.Research(0,200,100, 140);
    public static ResourceCost RESEARCH_SILVERFISH = ResourceCost.Research(0,300,300, 120);
    public static ResourceCost RESEARCH_SCULK_AMPLIFIERS = ResourceCost.Research(0,200,400, 150);
    public static ResourceCost RESEARCH_RAVAGER_ARTILLERY = ResourceCost.Research(400,0,350, 140);
    public static ResourceCost RESEARCH_BRUTE_SHIELDS = ResourceCost.Research(0,300,300, 150);
    public static ResourceCost RESEARCH_HOGLIN_CAVALRY = ResourceCost.Research(300,250,0, 100);
    public static ResourceCost RESEARCH_HEAVY_TRIDENTS = ResourceCost.Research(0, 250, 250, 120);
    public static ResourceCost RESEARCH_BLAZE_FIRE_WALL = ResourceCost.Research(400, 0, 300, 150);
    public static ResourceCost RESEARCH_FIRE_RESISTANCE = ResourceCost.Research(0, 200, 200, 100);
    public static ResourceCost RESEARCH_WITHER_CLOUDS = ResourceCost.Research(250, 0, 350, 150);
    public static ResourceCost RESEARCH_ADVANCED_PORTALS = ResourceCost.Research(0, 300, 300, 150);
    public static ResourceCost RESEARCH_CIVILIAN_PORTAL = ResourceCost.Research(0, 75, 0, 20);
    public static ResourceCost RESEARCH_MILITARY_PORTAL = ResourceCost.Research(0, 125, 0, 30);
    public static ResourceCost RESEARCH_TRANSPORT_PORTAL = ResourceCost.Research(0, 175, 0, 40);

    // ******************* ENCHANTMENTS ******************* //
    public static ResourceCost ENCHANT_MAIMING = ResourceCost.Enchantment(0,20, 30);
    public static ResourceCost ENCHANT_QUICK_CHARGE = ResourceCost.Enchantment(0,30, 15);
    public static ResourceCost ENCHANT_SHARPNESS = ResourceCost.Enchantment(0,40, 60);
    public static ResourceCost ENCHANT_MULTISHOT = ResourceCost.Enchantment(0,70, 35);
    public static ResourceCost ENCHANT_VIGOR = ResourceCost.Enchantment(0,60, 60);

    // ******************* Unused ******************* //

    public static ResourceCost RESEARCH_VINDICATOR_AXES = ResourceCost.Research(0,200,400, 150);
    public static ResourceCost RESEARCH_PILLAGER_CROSSBOWS = ResourceCost.Research(0,600,300, 180);
    public static ResourceCost ENDERMAN = ResourceCost.Unit(100,100,100,30,3);
}
