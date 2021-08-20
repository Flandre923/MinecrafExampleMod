package github.flandre.examplemod.core.registry;

import github.flandre.examplemod.core.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterPotion {
    @SubscribeEvent
    public static void onLivineHurt(LivingHurtEvent event){
        DamageSource source = event.getSource();
        if(source.getDamageType().equals("fall")){
            LivingEntity entityLiving = event.getEntityLiving();
            Effect effect = EffectInit.EXAMPLE_EFFECT.get();
            if(entityLiving.isPotionActive(effect)){
                EffectInstance activePotionEffect = entityLiving.getActivePotionEffect(effect);
                int level = activePotionEffect.getAmplifier();
                event.setAmount(level > 0 ? 0 : event.getAmount()/2);
            }
        }

    }
}
