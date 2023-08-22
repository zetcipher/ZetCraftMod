package net.zetcipher.zetcraft.init;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zetcipher.zetcraft.ZetCraft;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, ZetCraft.MOD_ID);

    public static final RegistryObject<PoiType> CAVE_PORTAL =
            POI.register("cave_portal", () -> new PoiType("cave_portal",
                    PoiType.getBlockStates(ModBlocks.CAVE_PORTAL_BLOCK.get()), 0, 1));

    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
