package github.flandre.examplemod.core.registry;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.core.init.ItemInit;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemModelsProperties.registerProperty(ItemInit.MAGIC_INGOT.get(),new ResourceLocation(ExampleMod.MODID,"size"),(itemStack,clientWorld,livingEntity)->itemStack.getCount());
        });
    }
}
