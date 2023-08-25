package net.zetcipher.zetcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZetCraftCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CAVE_NETHER_PORTAL_SWAP;

    public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACE_ARMOR_CALCULATION;
    public static final ForgeConfigSpec.ConfigValue<Float> MIN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Float> DANGER_THRESHOLD;
    public static final ForgeConfigSpec.ConfigValue<Float> POWER_RUSH_DMG_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Float> LAST_STAND_DMG_MOD;
    public static final ForgeConfigSpec.ConfigValue<Float> EMERALD_PENDANT_DROP_CHANCE;

    static {
        BUILDER.push("ZetCraft Common Config");

        MIN_DAMAGE = BUILDER.comment("The minimum damage that will be dealt to an entity on a LivingHurtEvent in half hearts (Default: 0.5)")
                .define("Minimum Damage Value", 0.5f);

        DANGER_THRESHOLD = BUILDER.comment("The maximum health value in half hearts a player can have and still be considered \"in danger\". (Default: 10.0)")
                .define("Danger Status Threshold", 10.0f);

        POWER_RUSH_DMG_BONUS = BUILDER.comment("The damage in half hearts that will be added onto a player's attack if wearing the Power Rush badge while their health is below the danger threshold. (Default: 4.0)")
                .define("Power Rush Damage Bonus", 4.0f);

        LAST_STAND_DMG_MOD = BUILDER.comment("The value the damage a player receives will be multiplied by if wearing the Last Stand badge while their health is below the danger threshold. (Default: 0.5)")
                .define("Last Stand Damage Multiplier", 0.5f);

        EMERALD_PENDANT_DROP_CHANCE = BUILDER.comment("The base drop chance of emeralds from mobs killed by the player while wearing an emerald pendant (0.1 = 10%). (Default: 0.1)")
                .define("Emerald Pendant Base Drop Rate", 0.1f);

        REPLACE_ARMOR_CALCULATION = BUILDER.comment("If true, Minecraft's own armor related damage reductions will be replaced by my own formula. This new formula allows low-tier armors to not be completely useless, and prevents the player from being nearly unkillable with maxed out equipment. (Default: false)")
                .define("Replace Vanilla Armor Formula", false);

        CAVE_NETHER_PORTAL_SWAP = BUILDER.comment("If true, using flint and steel on an obsidian portal frame will make a cave portal, and using a gate key instead will make a nether portal. (Default: false)")
                .define("Cave & Nether Portal Swap", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
