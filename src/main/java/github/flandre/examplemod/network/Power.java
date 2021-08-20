package github.flandre.examplemod.network;

import github.flandre.examplemod.common.mycapability.ExamplePower;
import github.flandre.examplemod.core.registry.CapabilityRegistryHandler;
import github.flandre.examplemod.core.registry.ModCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class Power {
    public float power;
    public Power(){
    }
    public Power(float power){
        this.power = power;
    }
    public float getPower() {
        return power;
    }
    public static void encode(Power p, PacketBuffer buffer){
        buffer.writeFloat(p.power);
    }
    public static Power decode(PacketBuffer buffer){
        return new Power(buffer.readFloat());
    }
    // 服务器处理逻辑
    public static void handlePacket(Power p, Supplier<NetworkEvent.Context> content){
        NetworkEvent.Context context = content.get();
        context.enqueueWork(()->{
            onClientCustomPack(p);
        });
        context.setPacketHandled(true);
    }
    @OnlyIn(Dist.CLIENT)
    public static void onClientCustomPack(Power p){
//            System.out.println("---------2-----------------");
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player; // 客户端对象
        LazyOptional<ExamplePower> capability = player.getCapability(ModCapability.EXAMPLE_POWER);
        capability.ifPresent((power) -> {
            power.setExamplePower(p.getPower());
        });
    }

}
