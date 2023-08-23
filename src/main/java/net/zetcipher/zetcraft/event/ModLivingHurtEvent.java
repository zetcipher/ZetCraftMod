package net.zetcipher.zetcraft.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zetcipher.zetcraft.init.ModItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

@Mod.EventBusSubscriber
public class ModLivingHurtEvent {
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent ev) {
        float newDamage = ev.getAmount();


        // Execute code for when player is attacking
        if (ev.getSource().getEntity() instanceof Player) {

            Player player = (Player) ev.getSource().getEntity();

            // Commented out because I need to add a different item to grant this effect.
            /*if (isPlayerWearingCurio(player, ModItems.STAR_PENDANT.get())) {
                float expDmgBonus = player.experienceLevel;
                expDmgBonus *= 0.1f;
                if (expDmgBonus > 30.0f) {expDmgBonus = 30.0f;}
                newDamage += expDmgBonus;
            }*/

            // This should always be the last damage modification done (at least in this mod)
            if (isPlayerWearingCurio(player, ModItems.RISK_RING.get())) { newDamage *= 2.0f; } // Doubling damage if Risk Ring is equipped. (Yes it happens on both ends, that's the point.)

        }


        // Execute code for when player is hurt
        if (ev.getEntityLiving() instanceof Player) {
            Player player = (Player) ev.getEntityLiving();

            // TODO: Implement dodge chance (Maybe should be in different event?)

            // This should always be the last damage modification done (at least in this mod)
            if (isPlayerWearingCurio(player, ModItems.RISK_RING.get())) { newDamage *= 2.0f; } // Doubling damage if Risk Ring is equipped. (Yes it happens on both ends, that's the point.)

        }


        if (newDamage < 0.5f) { newDamage = 0.5f; } // Setting minimum damage value. TODO: Make this value configurable
        if (newDamage != ev.getAmount()) { ev.setAmount(newDamage); } // Setting new damage value.
    }

    public static boolean isPlayerWearingCurio (Player player, Item item) {
        ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();
        return curiosHelper.findFirstCurio(player, item).isPresent();
    }
}
