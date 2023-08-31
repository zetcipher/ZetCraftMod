package net.zetcipher.zetcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZCServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CAVE_NETHER_PORTAL_SWAP;

    public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACE_ARMOR_CALCULATION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> NO_VANILLA_CRITS;
    public static final ForgeConfigSpec.ConfigValue<Float> MIN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Float> DANGER_THRESHOLD;
    public static final ForgeConfigSpec.ConfigValue<Float> POWER_RUSH_DMG_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> LAST_STAND_DMG_MOD;
    public static final ForgeConfigSpec.ConfigValue<Float> BASE_CRIT_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Float> STAR_PENDANT_LEVEL_THRESHOLD;
    public static final ForgeConfigSpec.ConfigValue<Float> DIAMOND_PENDANT_DAMAGE_SUBTRACTION;
    public static final ForgeConfigSpec.ConfigValue<Float> AMETHYST_PENDANT_EXP_MOD;
    public static final ForgeConfigSpec.ConfigValue<Float> LAPIS_PENDANT_LUCK_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> QUARTZ_PENDANT_CRIT_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Float> ENDER_PEARL_PENDANT_DODGE_CHANCE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> EMERALD_PENDANT_DROP_CHANCE;


    public static final ForgeConfigSpec.ConfigValue<Float> FOOD_HEAL_RATIO_BASE;
    public static final ForgeConfigSpec.ConfigValue<Float> FOOD_HEAL_RATIO_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> FOOD_HEAL_MAX;
    public static final ForgeConfigSpec.ConfigValue<Float> FOOD_EQUIP_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> FOOD_EQUIP_MOD;


    static {
        BUILDER.push("ZetCraft Server Config");
        BUILDER.comment("\n");
        BUILDER.comment("\n");
        BUILDER.comment("Combat & Related Equips");
        BUILDER.comment("\n");

        MIN_DAMAGE = BUILDER.comment("The minimum damage that will be dealt to an entity when hurt in half hearts (Default: 0.5)")
                .define("Minimum Damage Value", 0.5f);
        BUILDER.comment("\n");

        DANGER_THRESHOLD = BUILDER.comment("Without any other equips, the player must have this much health (in half hearts) or less to be considered \"in danger\". (Default: 10.0)")
                .define("Danger Status Threshold", 10.0f);
        BUILDER.comment("\n");

        POWER_RUSH_DMG_BONUS = BUILDER.comment("The damage in half hearts that will be added onto a player's attack if wearing the Power Rush badge while their health is below the danger threshold. (Default: 4.0)")
                .define("Power Rush Damage Bonus", 4.0f);
        BUILDER.comment("\n");

        LAST_STAND_DMG_MOD = BUILDER.comment("Damage taken will be reduced by this amount as a percentage for each Last Stand badge equipped while in danger. (Default: 0.33)")
                .define("Last Stand Damage Multiplier", 0.33f);
        BUILDER.comment("\n");

        BASE_CRIT_CHANCE = BUILDER.comment("The Player's base critical hit chance (0.05 = 5%). (Default: 0.05)")
                .define("Base Crit Chance", 0.05f);
        BUILDER.comment("\n");

        STAR_PENDANT_LEVEL_THRESHOLD = BUILDER.comment("How many XP levels does the player need for the Star Pendant to increase damage dealt by 1. (Default: 10)")
                .define("Star Pendant Level Threshold", 10f);
        BUILDER.comment("\n");

        DIAMOND_PENDANT_DAMAGE_SUBTRACTION = BUILDER.comment("Damage to subtract for each Diamond pendant equipped when the player is hurt. (Default: 1.0)")
                .define("Diamond Pendant Damage Subtraction", 1.0f);
        BUILDER.comment("\n");

        AMETHYST_PENDANT_EXP_MOD = BUILDER.comment("Experience multiplier to add for each amethyst pendant the Player equips. (0.25 = 25%). (Default: 0.25)")
                .define("Amethyst Pendant EXP Multiplier", 0.25f);
        BUILDER.comment("\n");

        LAPIS_PENDANT_LUCK_BONUS = BUILDER.comment("Luck to add for each Lapis Pendant the player equips. (Default: 2.0)")
                .define("Lapis Pendant Luck Bonus", 2.0f);
        BUILDER.comment("\n");

        QUARTZ_PENDANT_CRIT_CHANCE = BUILDER.comment("Percentage crit chance to add for each Nether Quartz Pendant the Player equips. (Default: 0.1)")
                .define("Quartz Pendant Crit Chance", 0.1f);
        BUILDER.comment("\n");

        ENDER_PEARL_PENDANT_DODGE_CHANCE_BONUS = BUILDER.comment("Percentage dodge chance to add for each ender pearl pendant the Player equips. (0.1 = 10%). (Default: 0.1)")
                .define("Ender Pearl Pendant Dodge Chance", 0.1f);
        BUILDER.comment("\n");

        EMERALD_PENDANT_DROP_CHANCE = BUILDER.comment("The base drop chance of emeralds from mobs killed by the player while wearing an emerald pendant (0.1 = 10%). (Default: 0.1)")
                .define("Emerald Pendant Base Drop Rate", 0.1f);
        BUILDER.comment("\n");


        NO_VANILLA_CRITS = BUILDER.comment("If true, Minecraft's standard critical hit system (striking while falling, fully charging a bow, etc) will be replaced by a random crit system. (Default: true)")
                .define("No Vanilla Crits", true);
        BUILDER.comment("\n");

        REPLACE_ARMOR_CALCULATION = BUILDER.comment("If true, Minecraft's own armor related damage reductions will be replaced by my own formula. This new formula allows low-tier armors to not be completely useless, and prevents the player from being nearly unkillable with maxed out equipment. (Default: false)")
                .define("Replace Vanilla Armor Formula", false);
        BUILDER.comment("\n");


        BUILDER.comment("\n");
        BUILDER.comment("Other Equips & Related Mechanics");
        BUILDER.comment("\n");

        FOOD_HEAL_RATIO_BASE = BUILDER.comment("The base ratio of health restored to the food value of an eaten food item. (0.5 = 2 hearts restored from cooked steak) (Default: 0.0)")
                .define("Base Food Healing Ratio", 0.0f);
        BUILDER.comment("\n");

        FOOD_HEAL_RATIO_BONUS = BUILDER.comment("The value added onto the previous ratio with the [FOOD HEALING EQUIP] accessory equipped. (0.5 = 2 hearts restored from cooked steak) (Default: 0.5)")
                .define("Bonus Food Healing Ratio", 0.5f);
        BUILDER.comment("\n");

        FOOD_HEAL_MAX = BUILDER.comment("The maximum health in half hearts that food can restore. This value is doubled with the [FOOD HEALING EQUIP] accessory equipped. (Default: 6.0)")
                .define("Food Healing Max", 6.0f);
        BUILDER.comment("\n");

        FOOD_EQUIP_BONUS = BUILDER.comment("The bonus added on to hunger restoration from food with the [FOOD BUFF EQUIP] accessory equipped. (Default: 2.0)")
                .define("Food Equip Restoration Bonus", 2.0f);
        BUILDER.comment("\n");

        FOOD_EQUIP_MOD = BUILDER.comment("The value to multiply hunger restoration from food by with the [FOOD BUFF EQUIP] accessory equipped. (Default: 1.5)")
                .define("Food Equip Restoration Multiplier", 1.5f);
        BUILDER.comment("\n");


        BUILDER.comment("\n");
        BUILDER.comment("Worldgen & Other stuff");
        BUILDER.comment("\n");

        CAVE_NETHER_PORTAL_SWAP = BUILDER.comment("If true, using flint and steel on an obsidian portal frame will make a cave portal, and using a gate key instead will make a nether portal. (Default: false)")
                .define("Cave & Nether Portal Swap", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
