package github.flandre.examplemod.common.item;

import github.flandre.examplemod.common.mycapability.ExamplePower;
import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import github.flandre.examplemod.core.registry.CapabilityRegistryHandler;
import github.flandre.examplemod.core.registry.ModCapability;
import github.flandre.examplemod.network.NetworkRegistryHandler;
import github.flandre.examplemod.network.Power;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class InfiniteSnowball extends Item {
    public InfiniteSnowball() {
        super(new Properties().group(ExampleGroup.exampleGroup));
    }
    // 物品右键
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //获得手上物品
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        // 播放音效
        worldIn.playSound(null,playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL,0.5f,0.4F / (random.nextFloat() * 0.4F + 0.8F));
        // 判断是否为服务器
        if(!worldIn.isRemote){
            LazyOptional<ExamplePower> capability = playerIn.getCapability(ModCapability.EXAMPLE_POWER);
            capability.ifPresent((c)->{
                if (c.getExamplePower() >= 4) {
                    // 创建实体
                    SnowballEntity snowballEntity = new SnowballEntity(worldIn,playerIn);
                    // 配置实体
                    snowballEntity.func_234612_a_(playerIn,playerIn.rotationPitch,playerIn.rotationYaw,0.0f,1.5f,1.0f);
                    // 生成实体
                    worldIn.addEntity(snowballEntity);
                    c.setExamplePower(c.getExamplePower()-4);
                    NetworkRegistryHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) playerIn),new Power(c.getExamplePower()));
                }
            });

        }
//        heldItem.shrink(1); 使用后物品数量减一
        return new ActionResult<>(ActionResultType.SUCCESS,heldItem);
    }
}
