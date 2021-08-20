package github.flandre.examplemod.network;

import github.flandre.examplemod.ExampleMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkRegistryHandler {
    public static SimpleChannel CHANNEL;
    private static final String VERSION = "0.1.0";
    private static int ID = 0;
    public static int nextID(){
        return ID++;
    }
    public static void registerMessage(){
        CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(ExampleMod.MODID,"power"),
                ()->VERSION,
                (version)-> version.equals(VERSION),
                (version)->version.equals(VERSION));
        CHANNEL.registerMessage(nextID(),Power.class,Power::encode,Power::decode,Power::handlePacket);
    }

}
