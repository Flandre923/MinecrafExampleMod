package github.flandre.examplemod.core.init.event;

import github.flandre.examplemod.common.entity.ExampleEntity;
import github.flandre.examplemod.core.init.EntityInit;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributesSetEvent {
    @SubscribeEvent
    public static void setupAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.EXAMPLE_ENTITY.get(), ExampleEntity.func_234301_m_().create());
    }
}
