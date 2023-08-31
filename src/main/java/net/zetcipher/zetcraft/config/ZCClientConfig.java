package net.zetcipher.zetcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZCClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("ZetCraft Client Config");

        // Configs here

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
