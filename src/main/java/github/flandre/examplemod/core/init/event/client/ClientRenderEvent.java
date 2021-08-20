package github.flandre.examplemod.core.init.event.client;

import github.flandre.examplemod.client.gui.ExampleHUD;
import github.flandre.examplemod.common.mycapability.ExamplePower;
import github.flandre.examplemod.core.init.ItemInit;
import github.flandre.examplemod.core.registry.CapabilityRegistryHandler;
import github.flandre.examplemod.core.registry.ModCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientRenderEvent {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderGameOverlayPost(RenderGameOverlayEvent event){
        if(RenderGameOverlayEvent.ElementType.ALL.equals(event.getType())){
            Minecraft minecraft = Minecraft.getInstance();
            Entity renderViewEntity = minecraft.getRenderViewEntity();
            if(renderViewEntity instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) renderViewEntity;
                ItemStack currentItem = playerEntity.inventory.getCurrentItem();
                Item item = currentItem.getItem();
                if(item.equals(ItemInit.INFINITE_SNOWBALL.get())){
                    LazyOptional<ExamplePower> capability = playerEntity.getCapability(ModCapability.EXAMPLE_POWER);
                    capability.ifPresent((c) -> {
                        float examplePower = c.getExamplePower();
                        ExampleHUD exampleHUD = new ExampleHUD(event.getMatrixStack());
                        exampleHUD.render(examplePower);
                    });
                }

            }
        }
    }

}
