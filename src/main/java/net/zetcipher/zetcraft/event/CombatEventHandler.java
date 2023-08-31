package net.zetcipher.zetcraft.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;


@Mod.EventBusSubscriber
public class CombatEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent ev) { // This event occurs when an entity is set to be hurt, but hasn't actually yet.
        float damage = ev.getAmount();
        LivingEntity attacker = (LivingEntity) ev.getSource().getEntity();
        LivingEntity defender = ev.getEntityLiving();
        int attRiskRingCount = 0;
        int attStarPendantCount = 0;
        int defLastStandCount = 0;

        float dodgeChance = 0.0f;
        boolean dodged = false;

        // Execute code for when a player is attacking
        if (attacker instanceof Player) {
            Player player = (Player) attacker;

            attStarPendantCount = getCurioCount(attacker, ModItems.STAR_PENDANT.get());
            if (attStarPendantCount > 0 && player.experienceLevel >= 10) {
                int expDmgBonus = player.experienceLevel;
                expDmgBonus /= ZCServerConfig.STAR_PENDANT_LEVEL_THRESHOLD.get();
                expDmgBonus *= attStarPendantCount;
                if (expDmgBonus < 0) {expDmgBonus = 0;}
                if (expDmgBonus > 10) {expDmgBonus = 10;}
                damage += expDmgBonus; // Adding Star Pedant's XP damage bonus
            }

            if (entityInDanger(attacker)) { damage += getCurioCount(attacker, ModItems.POWER_RUSH.get()) * ZCServerConfig.POWER_RUSH_DMG_BONUS.get(); } // Adding the power rush damage bonus if attacker is in danger

            attRiskRingCount = getCurioCount(attacker, ModItems.RISK_RING.get());
        }

        // Execute code for when a player is hurt
        if (defender instanceof Player) {
            defLastStandCount = getCurioCount(defender, ModItems.LAST_STAND.get());

            dodgeChance += getCurioCount(defender, ModItems.ENDER_PENDANT.get()) * ZCServerConfig.ENDER_PEARL_PENDANT_DODGE_CHANCE_BONUS.get();
            if (Math.random() < dodgeChance) {
                //ZetCraft.LOGGER.info("This attack should have been dodged.");
                dodged = true;
            }

            if (entityInDanger(defender) && defLastStandCount > 0) {
                for (int i = 0; i < defLastStandCount; i++){
                    damage -= damage * ZCServerConfig.LAST_STAND_DMG_MOD.get();
                }
            } // Multiplying damage by the last stand damage mod if defender is in danger
        }

        if (dodged) {
            ev.setAmount(0.0f);
            ev.setCanceled(true);
            return;
        }

        if (damage < ZCServerConfig.MIN_DAMAGE.get()) { damage = ZCServerConfig.MIN_DAMAGE.get(); } // Setting minimum damage value.

        if (attRiskRingCount > 0) { damage *= 2.0f * attRiskRingCount; } // Doubling damage for each equipped Risk Ring.

        if (damage != ev.getAmount()) { ev.setAmount(damage); } // Setting new damage value.
    }


    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent ev) { // This event occurs after damage reductions and such have already been processed.
        float damage = ev.getAmount();
        LivingEntity attacker = (LivingEntity) ev.getSource().getEntity();
        LivingEntity defender = ev.getEntityLiving();
        int defRiskRingCount = 0;

        // Execute code for when a player is hurt
        if (defender instanceof Player) {
            defRiskRingCount = getCurioCount(defender, ModItems.RISK_RING.get());
            if (isWearingCurio(defender, ModItems.DIAMOND_PENDANT.get())) {damage -= ZCServerConfig.DIAMOND_PENDANT_DAMAGE_SUBTRACTION.get();}
        }

        if (damage < ZCServerConfig.MIN_DAMAGE.get()) { damage = ZCServerConfig.MIN_DAMAGE.get(); } // Setting minimum damage value. Yes this is done twice, cry about it.

        if (defRiskRingCount > 0) { damage *= 2.0f * defRiskRingCount; } // Doubling damage for each equipped Risk Ring.
        // The risk ring modifier is being handled after setting the minimum damage to avoid risk ring risk mitigation.

        if (damage != ev.getAmount()) { ev.setAmount(damage); } // Setting new damage value.
    }


    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent ev) {
        LivingEntity killer = (LivingEntity) ev.getSource().getEntity();
        LivingEntity killed = ev.getEntityLiving();
        int killerEmeraldPendantCount = 0;

        // Execute code for when a player killed the entity
        if (killer instanceof Player) {
            killerEmeraldPendantCount = getCurioCount(killer, ModItems.EMERALD_PENDANT.get());
        }

        if (killed instanceof Creeper){
            Creeper creeper = (Creeper) killed;
            if (creeper.canDropMobsSkull()) {creeper.spawnAtLocation(ModItems.THUNDER_RAGE.get());} // Charged creepers drop a Thunder Rage
        }

        if (killerEmeraldPendantCount > 0 && Math.random() < killerEmeraldPendantCount * ZCServerConfig.EMERALD_PENDANT_DROP_CHANCE.get()) {
            killed.spawnAtLocation(Items.EMERALD); // Dropping an emerald if the emerald pendant check worked
        }

    }


    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent ev) {
        Player killer = ev.getAttackingPlayer();
        LivingEntity killed = ev.getEntityLiving();
        int expAmount = ev.getOriginalExperience();
        int killerAmethystPendantCount = getCurioCount(killer, ModItems.AMETHYST_PENDANT.get());

        if (killerAmethystPendantCount > 0) {
            expAmount += expAmount * killerAmethystPendantCount * ZCServerConfig.AMETHYST_PENDANT_EXP_MOD.get();
        }

        ev.setDroppedExperience(expAmount);
    }


    // This function should be used for special equip effects that should not be stackable.
    public static boolean isWearingCurio (LivingEntity entity, Item item) {
        ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();
        return curiosHelper.findFirstCurio(entity, item).isPresent();
    }


    // This function should be used for special equip effects that should be stackable.
    public static int getCurioCount (LivingEntity entity, Item item) {
        ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();
        return curiosHelper.findCurios(entity, item).size();
    }


    public static boolean entityInDanger (LivingEntity entity) {
        return entity.getHealth() <= ZCServerConfig.DANGER_THRESHOLD.get();
    }
}
