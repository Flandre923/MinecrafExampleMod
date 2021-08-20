package github.flandre.examplemod.common.mycapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ExamplePower implements INBTSerializable<CompoundNBT> {
    private float examplePower;

    public ExamplePower() {
        this.examplePower = 0f;
    }

    public float getExamplePower() {
        return examplePower;
    }

    public void setExamplePower(float examplePower) {
        this.examplePower = examplePower;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("example_power",examplePower);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.examplePower = nbt.getFloat("example_power");
    }
}
