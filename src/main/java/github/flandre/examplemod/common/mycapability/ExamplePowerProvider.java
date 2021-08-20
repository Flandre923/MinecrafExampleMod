package github.flandre.examplemod.common.mycapability;

import github.flandre.examplemod.core.registry.CapabilityRegistryHandler;
import github.flandre.examplemod.core.registry.ModCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExamplePowerProvider implements ICapabilitySerializable<CompoundNBT> {
    private ExamplePower instance;

    public ExamplePowerProvider() {
        this.instance = new ExamplePower();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.EXAMPLE_POWER ? LazyOptional.of(()-> this.instance).cast() : LazyOptional.empty();
    }


    @Override
    public CompoundNBT serializeNBT() {
        return instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.instance.deserializeNBT(nbt);
    }
}
