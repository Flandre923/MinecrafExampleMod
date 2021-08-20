package github.flandre.examplemod.core.registry;

import github.flandre.examplemod.common.mycapability.ExamplePower;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(ExamplePower.class)
    public static Capability<ExamplePower> EXAMPLE_POWER;
}