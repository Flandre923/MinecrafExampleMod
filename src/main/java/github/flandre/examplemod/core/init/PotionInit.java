package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ExampleMod.MODID);

    public static final RegistryObject<Potion> EXAPMLE_POTION =
            POTION_TYPES.register("example_potion", // 名字
                    ()-> new Potion(new EffectInstance(EffectInit.EXAMPLE_EFFECT.get(), // 药水类型
                            3600))); // 时间长度

    public static final RegistryObject<Potion> STRONG_EXAPMLE_POTION =
            POTION_TYPES.register("strong_example_potion", // 名字
                    ()-> new Potion(new EffectInstance(EffectInit.EXAMPLE_EFFECT.get(), // 药水类型
                            1800,1))); // 时间长度

    public static final RegistryObject<Potion> LONG_EXAPMLE_POTION =
            POTION_TYPES.register("long_example_potion", // 名字
                    ()-> new Potion(new EffectInstance(EffectInit.EXAMPLE_EFFECT.get(), // 药水类型
                            9800))); // 时间长度
}
