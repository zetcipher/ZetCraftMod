package net.zetcipher.zetcraft.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.block.CavePortalBlock;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;
import net.zetcipher.zetcraft.init.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ZetCraft.MOD_ID);


    public static final RegistryObject<Block> STAR_ORE = registerBlock("star_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.ZETCRAFT);

    public static final RegistryObject<Block> STAR_DEEPSLATE_ORE = registerBlock("star_deepslate_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.ZETCRAFT);

    public static final RegistryObject<Block> STAR_ENDSTONE_ORE = registerBlock("star_endstone_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.ZETCRAFT);

    public static final RegistryObject<Block> STAR_BLOCK = registerBlock("star_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.ZETCRAFT);

    public static final RegistryObject<Block> DIRT_BOMB = registerBlock("dirt_bomb",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(0f).explosionResistance(0f).instabreak()), ModCreativeModeTab.ZETCRAFT);

    public static final RegistryObject<Block> CAVE_PORTAL_BLOCK = registerBlockWithoutBlockItem("cave_portal",
            CavePortalBlock::new);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
