package net.zetcipher.zetcraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.zetcipher.zetcraft.init.ModItems;

public class ModCreativeModeTab {
    public static final CreativeModeTab ZETCRAFT = new CreativeModeTab("zetcrafttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZET.get());
        }
    };
    public static final CreativeModeTab ZC_EQUIPS = new CreativeModeTab("zetcraftequips") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RISK_RING.get());
        }
    };
}
