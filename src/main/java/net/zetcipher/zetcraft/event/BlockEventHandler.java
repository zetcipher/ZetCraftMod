package net.zetcipher.zetcraft.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.block.CavePortalBlock;
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModBlocks;
import net.zetcipher.zetcraft.world.dimension.ModDimensions;

@Mod.EventBusSubscriber
public class BlockEventHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();

        if (ZCServerConfig.CAVE_NETHER_PORTAL_SWAP.get()) {
            if (player.getLevel().dimension() == ModDimensions.DEEPCAVERNDIM_KEY && player.getInventory().getSelected().is(Items.FLINT_AND_STEEL)) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = event.getPos().relative(direction);
                    ZetCraft.LOGGER.info("Still befriending portals... " + framePos.toString());
                    if (((CavePortalBlock) ModBlocks.CAVE_PORTAL_BLOCK.get()).trySpawnPortal(player.getLevel(), framePos)) {
                        player.getLevel().playSound(player, framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 1.0f);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTrySpawnPortal(BlockEvent.PortalSpawnEvent portalSpawnEvent) {
        //ZetCraft.LOGGER.info("Portal... Oh, Portal...");
        //ZetCraft.LOGGER.info(portalSpawnEvent.getState().toString());
        //ZetCraft.LOGGER.info("Good night, Portal. " + portalSpawnEvent.getPos().toString());
        if (ZCServerConfig.CAVE_NETHER_PORTAL_SWAP.get()) {
            if (portalSpawnEvent.getState().is(Blocks.FIRE)) {
                portalSpawnEvent.setCanceled(true);
                portalSpawnEvent.getWorld().destroyBlock(portalSpawnEvent.getPos(), false);
                CavePortalBlock cavePortalBlock = (CavePortalBlock) ModBlocks.CAVE_PORTAL_BLOCK.get();
                if (cavePortalBlock.trySpawnPortal(portalSpawnEvent.getWorld(), portalSpawnEvent.getPos())) {
                    ZetCraft.LOGGER.info("Good Morning, Saki.");
                } else {
                    ZetCraft.LOGGER.info("Hmph! Some portal you turned out to be!");
                }
            }
        }
    }
}
