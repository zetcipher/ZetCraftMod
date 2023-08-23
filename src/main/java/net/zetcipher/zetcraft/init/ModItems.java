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
import net.zetcipher.zetcraft.item.wearable.WearableItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZetCraft.MOD_ID);


    public static final RegistryObject<Item> ZET = ITEMS.register("zet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<Item> STAR_PIECE = ITEMS.register("star_piece",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<Item> PLAYER_SOUL_I = ITEMS.register("plr_soul_i",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<Item> PLAYER_SOUL_II = ITEMS.register("plr_soul_ii",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<Item> PLAYER_SOUL_III = ITEMS.register("plr_soul_iii",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));


    // Special

    public static final RegistryObject<ThunderRageItem> THUNDER_RAGE = ITEMS.register("thunder_rage",
            () -> new ThunderRageItem(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GATE_KEY = ITEMS.register("gate_key", GateKeyItem::new);


    // Wearables
    // Any equip that has a conditional/variable damage bonus (including risk ring) is handled in net.zetcipher.event.ModLivingHurtEvent

    public static final RegistryObject<WearableItem> RISK_RING = ITEMS.register("risk_ring", (WearableItem::new));
    public static final RegistryObject<WearableItem> STAR_PENDANT = ITEMS.register("star_pendant", (WearableItem::new));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
