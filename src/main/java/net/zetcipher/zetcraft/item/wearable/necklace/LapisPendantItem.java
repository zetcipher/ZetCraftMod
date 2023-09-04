package net.zetcipher.zetcraft.item.wearable.necklace;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class LapisPendantItem extends Item implements ICurioItem {
    public LapisPendantItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.ZC_EQUIPS)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack itemStack) {
        Multimap<Attribute, AttributeModifier> result = ICurioItem.super.getAttributeModifiers(slotContext, uuid, itemStack);
        float luckBonus = ZCServerConfig.LAPIS_PENDANT_LUCK_BONUS.get();;
        result.put(Attributes.LUCK, new AttributeModifier(uuid, new ResourceLocation(ZetCraft.MOD_ID, "lapis_pendant_luck").toString(), luckBonus, AttributeModifier.Operation.ADDITION));
        return result;
    }
}
