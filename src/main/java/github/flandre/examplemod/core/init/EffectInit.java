package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.potion.ExampleEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ExampleMod.MODID);

    public static final RegistryObject<Effect> EXAMPLE_EFFECT = POTIONS.register("example_potion", ExampleEffect::new);


}
