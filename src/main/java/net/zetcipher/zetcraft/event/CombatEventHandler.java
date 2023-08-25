package net.zetcipher.zetcraft.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zetcipher.zetcraft.config.ZetCraftCommonConfigs;
import net.zetcipher.zetcraft.init.ModItems;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

import java.util.Collection;
import java.util.Iterator;


@Mod.EventBusSubscriber
public class CombatEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent ev) {
        float damage = ev.getAmount();
        LivingEntity attacker = (LivingEntity) ev.getSource().getEntity();
        LivingEntity defender = ev.getEntityLiving();
        int attRiskRingCount = 0;
        int defRiskRingCount = 0;


        // Execute code for when a player is attacking
        if (attacker instanceof Player) {

            // Commented out because I need to add a different item to grant this effect.
            /*if (isPlayerWearingCurio(attacker, ModItems.STAR_PENDANT.get())) {
                float expDmgBonus = player.experienceLevel;
                expDmgBonus *= 0.1f;
                if (expDmgBonus > 30.0f) {expDmgBonus = 30.0f;}
                newDamage += expDmgBonus;
            }*/

            if (entityInDanger(attacker)) { damage += getCurioCount(attacker, ModItems.POWER_RUSH.get()) * ZetCraftCommonConfigs.POWER_RUSH_DMG_BONUS.get(); } // Adding the power rush damage bonus if attacker is in danger

            attRiskRingCount = getCurioCount(attacker, ModItems.RISK_RING.get());
        }

        // Execute code for when a player is hurt
        if (defender instanceof Player) {

            // TODO: Implement dodge chance

            if (entityInDanger(defender) && isWearingCurio(defender, ModItems.LAST_STAND.get())) { damage *= ZetCraftCommonConfigs.LAST_STAND_DMG_MOD.get(); } // Multiplying damage by the last stand damage mod if defender is in danger

            defRiskRingCount = getCurioCount(defender, ModItems.RISK_RING.get());
        }

        if (damage < ZetCraftCommonConfigs.MIN_DAMAGE.get()) { damage = ZetCraftCommonConfigs.MIN_DAMAGE.get(); } // Setting minimum damage value.

        if (attRiskRingCount > 0) { damage *= 2.0f * attRiskRingCount; } // Doubling damage for each equipped Risk Ring. This is being done after setting the minimum damage to avoid Risk Ring risk mitigation.
        if (defRiskRingCount > 0) { damage *= 2.0f * defRiskRingCount; } // No further damage alterations should be done after this.

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

        if (killerEmeraldPendantCount > 0 && Math.random() < killerEmeraldPendantCount * ZetCraftCommonConfigs.EMERALD_PENDANT_DROP_CHANCE.get()) {
            killed.spawnAtLocation(Items.EMERALD); // Dropping an emerald if the emerald pendant check worked
        }


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
        return entity.getHealth() <= ZetCraftCommonConfigs.DANGER_THRESHOLD.get();
    }
}
