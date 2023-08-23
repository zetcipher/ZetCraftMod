package net.zetcipher.zetcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZetCraftCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CAVE_PORTAL_FRAME_BLOCK;

    static {
        BUILDER.push("ZetCraft Common Config");

        CAVE_PORTAL_FRAME_BLOCK = BUILDER.comment("What block is the cave portal made of? (Should be same as data/zetcraft/tags/blocks/cave_portal_frame.json)")
                        .define("Cave Portal Frame", "zetcraft:star_block");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
