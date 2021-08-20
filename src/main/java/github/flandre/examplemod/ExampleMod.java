package github.flandre.examplemod;

import github.flandre.examplemod.client.gui.ExampleGUI;
import github.flandre.examplemod.core.init.*;
import github.flandre.examplemod.core.init.event.EntitySpawnEvent;
import github.flandre.examplemod.core.init.event.OreGenerationEvent;
import github.flandre.examplemod.network.NetworkRegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "examplemod";
    public ExampleMod() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EnchantmentInit.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        EffectInit.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PotionInit.POTION_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        EntityInit.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        // 这个tileentity
        TileEntityInit.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());//bug

        ContainerInit.CONTAINER_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        // 生物生成事件

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, EntitySpawnEvent::BiomeLoading);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGenerationEvent::generateOres);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetUp);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

    }


    public void onCommonSetUp(FMLCommonSetupEvent event){
        NetworkRegistryHandler.registerMessage();
    }

    public void onClientSetup(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ContainerInit.EXAMPLE_GUI_CONTIANER.get(), ExampleGUI::new);
    }

}
