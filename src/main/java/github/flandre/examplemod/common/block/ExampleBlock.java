package github.flandre.examplemod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.lwjgl.system.CallbackI;

public class ExampleBlock extends Block {

    public ExampleBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1.5f,10f));
    }
}
