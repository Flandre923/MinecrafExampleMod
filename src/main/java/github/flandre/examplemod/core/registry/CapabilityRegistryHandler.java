package github.flandre.examplemod.core.registry;

import github.flandre.examplemod.common.mycapability.ExamplePower;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CapabilityRegistryHandler {

    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event){
        event.enqueueWork(()->{
            CapabilityManager.INSTANCE.register(ExamplePower.class,
                    new Capability.IStorage<ExamplePower>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<ExamplePower> capability, ExamplePower instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<ExamplePower> capability, ExamplePower instance, Direction side, INBT nbt) {

                        }
                    },()->null);
                }
        );
    }

}
