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
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;

import java.util.List;

public class PowerRushItem extends Item {
    public PowerRushItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.ZC_EQUIPS)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        float dmgBonus = ZCServerConfig.POWER_RUSH_DMG_BONUS.get();
        tooltip.add(new TranslatableComponent("info.zetcraft.danger_condition").withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("info.zetcraft.damage_dealt_all", dmgBonus).withStyle(ChatFormatting.BLUE));
    }
}
