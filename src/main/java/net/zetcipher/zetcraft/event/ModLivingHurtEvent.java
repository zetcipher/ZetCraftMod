package net.zetcipher.zetcraft.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zetcipher.zetcraft.ZetCraft;
import net.zetcipher.zetcraft.item.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class ModLivingHurtEvent {
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent ev) {
        float newDamage = ev.getAmount();
        boolean attRiskRing = false;
        boolean defRiskRing = false;


        // Execute code for when player is attacking
        if (ev.getSource().getEntity() instanceof Player) {
            Player player = (Player) ev.getSource().getEntity();

            // TODO: Risk Ring check

        }


        // Execute code for when player is hurt
        if (ev.getEntityLiving() instanceof Player) {
            Player player = (Player) ev.getEntityLiving();

            // TODO: Risk Ring check

        }

        if (newDamage != ev.getAmount()) { ev.setAmount(newDamage); }
    }
}
