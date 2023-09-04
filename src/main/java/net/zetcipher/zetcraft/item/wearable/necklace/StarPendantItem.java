package net.zetcipher.zetcraft.item.wearable.necklace;

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
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;

import java.util.List;

public class StarPendantItem extends Item {
    public StarPendantItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.ZC_EQUIPS)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        int levelsNeeded = (int) (ZCServerConfig.STAR_PENDANT_LEVEL_THRESHOLD.get() * 1);
        tooltip.add(new TranslatableComponent("info.zetcraft.dmg_per_level", levelsNeeded).withStyle(ChatFormatting.BLUE));
        tooltip.add(new TranslatableComponent("tooltip." + stack.getItem().getRegistryName().toString()).withStyle(ChatFormatting.GRAY));
    }
}
