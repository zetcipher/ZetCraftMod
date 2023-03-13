package net.zetcipher.zetcraft.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ZETCRAFT = new CreativeModeTab("zetcrafttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZET.get());
        }
    };
}
