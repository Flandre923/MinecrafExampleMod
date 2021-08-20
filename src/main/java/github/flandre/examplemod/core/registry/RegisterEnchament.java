package github.flandre.examplemod.core.registry;

import github.flandre.examplemod.core.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterEnchament {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event){
        Entity source = event.getSource().getImmediateSource();
        if(source instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source;
            ItemStack heldItem = player.getHeldItemMainhand();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXAMPLE_ENCHANTMENT.get(),heldItem);
            if(level>0){
                Entity entity = event.getEntity();
                entity.world.createExplosion(null,entity.getPosX(),entity.getPosY(),entity.getPosZ(),level, Explosion.Mode.NONE);
            }
        }
    }
}
