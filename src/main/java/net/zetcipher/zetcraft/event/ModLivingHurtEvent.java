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

            if (isPlayerWearingCurio(player, ModItems.RISK_RING.get())) {
                newDamage *= 2.0f;
            }

        }


        // Execute code for when player is hurt
        if (ev.getEntityLiving() instanceof Player) {
            Player player = (Player) ev.getEntityLiving();

            if (isPlayerWearingCurio(player, ModItems.RISK_RING.get())) {
                newDamage *= 2.0f;
            }

        }

        if (newDamage != ev.getAmount()) { ev.setAmount(newDamage); }
    }

    public static boolean isPlayerWearingCurio (Player player, Item item) {
        ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();
        return curiosHelper.findFirstCurio(player, item).isPresent();
    }
}
