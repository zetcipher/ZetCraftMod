package net.zetcipher.zetcraft.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.item.ThunderRageItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZetCraft.MOD_ID);


    public static final RegistryObject<Item> ZET = ITEMS.register("zet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<Item> STAR_PIECE = ITEMS.register("star_piece",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT)));

    public static final RegistryObject<ThunderRageItem> THUNDER_RAGE = ITEMS.register("thunder_rage",
            () -> new ThunderRageItem(new Item.Properties().tab(ModCreativeModeTab.ZETCRAFT).stacksTo(1).rarity(Rarity.UNCOMMON)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
