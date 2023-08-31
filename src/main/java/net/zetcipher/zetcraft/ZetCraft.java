package net.zetcipher.zetcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zetcipher.zetcraft.config.ZCClientConfig;
import net.zetcipher.zetcraft.config.ZCCommonConfig;
import net.zetcipher.zetcraft.config.ZCServerConfig;
import net.zetcipher.zetcraft.init.ModBlocks;
import net.zetcipher.zetcraft.init.ModItems;
import net.zetcipher.zetcraft.init.ModPOIs;
import net.zetcipher.zetcraft.world.dimension.ModDimensions;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZetCraft.MOD_ID)
public class ZetCraft
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "zetcraft";

    public static AABB getAABBfromEntity(LivingEntity entity, int radius) {
        return new AABB(entity.getX() - radius, entity.getY() - radius, entity.getZ() - radius, entity.getX() + radius, entity.getY() + radius, entity.getZ() + radius);
    }

    public ZetCraft()
    {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModDimensions.register();

        ModPOIs.register(eventBus);

        eventBus.addListener(this::enqueueIMC);

        eventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ZCClientConfig.SPEC, "zetcraft-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ZCCommonConfig.SPEC, "zetcraft-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ZCServerConfig.SPEC, "zetcraft-server.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    public void enqueueIMC(final InterModEnqueueEvent event) {
        SlotTypePreset[] types = {SlotTypePreset.RING, SlotTypePreset.NECKLACE, SlotTypePreset.CHARM};
        for (SlotTypePreset type : types) {
            InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> type.getMessageBuilder().build());
        }
    }
}
