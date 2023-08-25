package net.zetcipher.zetcraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.block.CavePortalBlock;
import net.zetcipher.zetcraft.config.ZetCraftCommonConfigs;
import net.zetcipher.zetcraft.init.ModBlocks;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;
import net.zetcipher.zetcraft.world.dimension.ModDimensions;

import java.util.List;
import java.util.Optional;

public class GateKeyItem extends Item {
    public GateKeyItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.ZETCRAFT)
                .stacksTo(1)
                .rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (ZetCraftCommonConfigs.CAVE_NETHER_PORTAL_SWAP.get()) {
            if (context.getPlayer().level.dimension() == Level.NETHER || context.getPlayer().level.dimension() == Level.OVERWORLD) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    ZetCraft.LOGGER.info("Wow, a gate key? Is that a [Licensed Property] reference? " + framePos.toString());
                    Optional<PortalShape> optional = PortalShape.findEmptyPortalShape(context.getLevel(), framePos, Direction.Axis.X);
                    optional = net.minecraftforge.event.ForgeEventFactory.onTrySpawnPortal(context.getLevel(), framePos, optional);
                    if (optional.isPresent()) {
                        optional.get().createPortalBlocks();
                    }
                }
            }
        } else {
            if (context.getPlayer().level.dimension() == ModDimensions.DEEPCAVERNDIM_KEY || context.getPlayer().level.dimension() == Level.OVERWORLD) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    ZetCraft.LOGGER.info("Wow, a gate key? Is that a [Licensed Property] reference? " + framePos.toString());
                    if (((CavePortalBlock) ModBlocks.CAVE_PORTAL_BLOCK.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 1.0f);
                        return InteractionResult.CONSUME;
                    }
                    else return InteractionResult.FAIL;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        tooltip.add(new TranslatableComponent("tooltip.zetcraft:gate_key").withStyle(ChatFormatting.GRAY));
    }
}
