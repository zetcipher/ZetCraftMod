package net.zetcipher.zetcraft.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zetcipher.zetcraft.ZetCraft;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> XRAYSCOPE_FINDABLES = tag("xray_scope_findables");
        public static final TagKey<Block> NETHERPORTAL_FRAME = tag("nether_portal_frame");
        public static final TagKey<Block> CAVEPORTAL_FRAME = tag("cave_portal_frame");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ZetCraft.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        //public static final TagKey<Item> ITEMTAG_KEY = null;

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ZetCraft.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

    public static boolean isInBlockTag(Block block, TagKey tag) {
        return Registry.BLOCK.getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get()).is(tag);
    }

    public static boolean isInItemTag(Item item, TagKey tag) {
        return Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(item).get()).is(tag);
    }
}
