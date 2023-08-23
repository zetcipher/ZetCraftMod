package net.zetcipher.zetcraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.zetcipher.zetcraft.block.CavePortalBlock;
import net.zetcipher.zetcraft.init.ModBlocks;
import net.zetcipher.zetcraft.init.ModCreativeModeTab;
import net.zetcipher.zetcraft.world.dimension.ModDimensions;

public class GateKeyItem extends Item {
    public GateKeyItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.ZETCRAFT)
                .stacksTo(1)
                .rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer().level.dimension() == ModDimensions.DEEPCAVERNDIM_KEY || context.getPlayer().level.dimension() == Level.OVERWORLD) {
            for (Direction direction : Direction.Plane.VERTICAL) {
                BlockPos framePos = context.getClickedPos().relative(direction);
                if (((CavePortalBlock) ModBlocks.CAVE_PORTAL_BLOCK.get()).trySpawnPortal(context.getLevel(), framePos)) {
                    context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 1.0f);
                    return InteractionResult.CONSUME;
                }
                else return InteractionResult.FAIL;
            }
        }
        return InteractionResult.FAIL;
    }
}
