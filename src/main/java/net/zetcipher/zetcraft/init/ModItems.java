package net.zetcipher.zetcraft.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.item.GateKeyItem;
import net.zetcipher.zetcraft.item.ThunderRageItem;
import net.zetcipher.zetcraft.item.wearable.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZetCraft.MOD_ID);


    public static final RegistryObject<Item> ZET = ITEMS.register("zet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> STAR_PIECE = ITEMS.register("star_piece",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> RAW_X = ITEMS.register("raw_x",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> RAW_Y = ITEMS.register("raw_y",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> RAW_Z = ITEMS.register("raw_z",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> X_INGOT = ITEMS.register("x_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> Y_INGOT = ITEMS.register("y_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> Z_INGOT = ITEMS.register("z_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> PLAYER_SOUL_I = ITEMS.register("plr_soul_i",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> PLAYER_SOUL_II = ITEMS.register("plr_soul_ii",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));
    public static final RegistryObject<Item> PLAYER_SOUL_III = ITEMS.register("plr_soul_iii",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));


    // Special

    public static final RegistryObject<ThunderRageItem> THUNDER_RAGE = ITEMS.register("thunder_rage", ThunderRageItem::new);
    public static final RegistryObject<Item> GATE_KEY = ITEMS.register("gate_key", GateKeyItem::new);


    // Wearables
    // Generic wearables (i.e. those that don't have configurable bonuses, or don't list specific values) come from the WearableItem class.

    public static final RegistryObject<WearableItem> RISK_RING = ITEMS.register("risk_ring", WearableItem::new);
    public static final RegistryObject<StarPendantItem> STAR_PENDANT = ITEMS.register("star_pendant", StarPendantItem::new);
    public static final RegistryObject<LapisPendantItem> LAPIS_PENDANT = ITEMS.register("lapis_pendant", LapisPendantItem::new);
    public static final RegistryObject<WearableItem> AMETHYST_PENDANT = ITEMS.register("amethyst_pendant", WearableItem::new);
    public static final RegistryObject<WearableItem> DIAMOND_PENDANT = ITEMS.register("diamond_pendant", WearableItem::new);
    public static final RegistryObject<WearableItem> EMERALD_PENDANT = ITEMS.register("emerald_pendant", WearableItem::new);
    public static final RegistryObject<WearableItem> QUARTZ_PENDANT = ITEMS.register("nether_quartz_pendant", WearableItem::new);
    public static final RegistryObject<EnderPearlPendantItem> ENDER_PENDANT = ITEMS.register("ender_pearl_pendant", EnderPearlPendantItem::new);

    public static final RegistryObject<PowerRushItem> POWER_RUSH = ITEMS.register("power_rush", PowerRushItem::new);
    public static final RegistryObject<LastStandItem> LAST_STAND = ITEMS.register("last_stand", LastStandItem::new);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
