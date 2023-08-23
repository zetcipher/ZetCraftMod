package net.zetcipher.zetcraft.item.wearable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;

import java.util.List;

public class WearableItem extends Item {

    public WearableItem(Properties properties) {
        super(properties.stacksTo(1).tab(ModCreativeModeTab.ZETCRAFT).rarity(Rarity.RARE).fireResistant());
    }
    public WearableItem() {
        this(new Properties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        tooltip.add(new TranslatableComponent("tooltip." + stack.getItem().getRegistryName().toString()).withStyle(ChatFormatting.GRAY));
    }
}
