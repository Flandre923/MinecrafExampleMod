package github.flandre.examplemod.core.init.event;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.entity.ExampleEntity;
import github.flandre.examplemod.common.mycapability.ExamplePower;
import github.flandre.examplemod.common.mycapability.ExamplePowerProvider;
import github.flandre.examplemod.core.registry.CapabilityRegistryHandler;
import github.flandre.examplemod.core.registry.ModCapability;
import github.flandre.examplemod.network.NetworkRegistryHandler;
import github.flandre.examplemod.network.Power;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class ExmplePowerEvent {
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event){
        Entity object = event.getObject();
        if (object instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(ExampleMod.MODID,"example_power"),new ExamplePowerProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event){
        Entity entity = event.getEntity();
        if(!event.getWorld().isRemote && entity instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) entity;
            LazyOptional<ExamplePower> capability = playerEntity.getCapability(ModCapability.EXAMPLE_POWER);
            capability.ifPresent((c)->{
                float examplePower = c.getExamplePower();
                playerEntity.sendMessage(new StringTextComponent("your example power is :"+examplePower),playerEntity.getUniqueID());
                NetworkRegistryHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) playerEntity),new Power(c.getExamplePower()));
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        PlayerEntity player = event.getPlayer();
        PlayerEntity original = event.getOriginal();

        LazyOptional<ExamplePower> capability = player.getCapability(ModCapability.EXAMPLE_POWER);
        LazyOptional<ExamplePower> originalCapability = original.getCapability(ModCapability.EXAMPLE_POWER);

        if(capability.isPresent()&& originalCapability.isPresent()){
            capability.ifPresent((cap)->{
                originalCapability.ifPresent(originalCap->{
                    cap.setExamplePower(originalCap.getExamplePower());
                    NetworkRegistryHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) player),new Power(cap.getExamplePower()));

                });
            });
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event){
        LivingEntity entityLiving = event.getEntityLiving();
        if(entityLiving instanceof ExampleEntity){
            float amount = Math.min(entityLiving.getHealth(),event.getAmount());
            Entity trueSource = event.getSource().getTrueSource();
            if(trueSource instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) trueSource;
                LazyOptional<ExamplePower> capability = playerEntity.getCapability(ModCapability.EXAMPLE_POWER);
                capability.ifPresent((c)->{
                    c.setExamplePower(c.getExamplePower()+amount);
                    playerEntity.sendMessage(new StringTextComponent("add example power:"+amount+"  your total example power is "+c.getExamplePower())
                            ,playerEntity.getUniqueID());
                    NetworkRegistryHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) playerEntity),new Power(c.getExamplePower()));
                });


            }
        }

    }


}
