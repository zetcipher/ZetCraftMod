package net.zetcipher.zetcraft.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.zetcipher.zetcraft.ZetCraft;

public class ModDimensions {
    public static final ResourceKey<Level> DEEPCAVERNDIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(ZetCraft.MOD_ID, "deep_caverns_dimension"));
    public static final ResourceKey<DimensionType> DEEPCAVERNDIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, DEEPCAVERNDIM_KEY.getRegistryName());

    public static void register() {
        System.out.println("Registering ModDimensions for " + ZetCraft.MOD_ID);
    }
}
