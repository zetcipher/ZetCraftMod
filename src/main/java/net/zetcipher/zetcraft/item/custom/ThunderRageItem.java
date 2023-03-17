package net.zetcipher.zetcraft.item.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.item.ModItems;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class ThunderRageItem extends Item {
    public ThunderRageItem(Properties pProperties) {
        super(pProperties);
    }

    public List<Monster> getEntsInRadius(Level level, LivingEntity entity, int radius) {
        return level.getNearbyEntities(Monster.class, TargetingConditions.DEFAULT, entity, ZetCraft.getAABBfromEntity(entity, radius));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            //ZetCraft.LOGGER.info("test");

            Player player = pContext.getPlayer();
            Level level = pContext.getLevel();

            boolean success = false;

            List<Monster> enemies = getEntsInRadius(pContext.getLevel(), player, 16);

            for (Monster monsterIterator : enemies) {
                monsterIterator.hurt(DamageSource.playerAttack(player).bypassArmor().setMagic(), 30);
                if (level instanceof ServerLevel) {
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                    lightning.moveTo(Vec3.atBottomCenterOf(new BlockPos(monsterIterator.getX(), monsterIterator.getY(), monsterIterator.getZ())));
                    lightning.setVisualOnly(true);
                    level.addFreshEntity(lightning);
                }
                if (!success) {success = true;}
            }

            if (success) {
                MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
                if (_mcserv != null) {_mcserv.getPlayerList().broadcastMessage(new TextComponent((("<" + (player.getDisplayName().getString() + "> ")) + "Eat this! Thunderbolt!")), ChatType.SYSTEM, Util.NIL_UUID);}
                ItemStack item = new ItemStack(ModItems.THUNDER_RAGE.get());
                if (!player.isCreative()) {
                    player.getInventory().clearOrCountMatchingItems(p -> item.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                }
            }

            //ZetCraft.LOGGER.info(enemies.toString());
        }

        return super.useOn(pContext);
    }
}
