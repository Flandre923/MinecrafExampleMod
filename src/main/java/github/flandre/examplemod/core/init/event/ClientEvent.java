package github.flandre.examplemod.core.init.event;

import github.flandre.examplemod.common.entity.render.ExampleEntityRender;
import github.flandre.examplemod.core.init.EntityInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.swing.text.html.parser.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            Minecraft mc = Minecraft.getInstance();
            EntityRendererManager manager = mc.getRenderManager();
            manager.register(EntityInit.EXAMPLE_ENTITY.get(),new ExampleEntityRender(manager));
        });
    }


}
