package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.block.CustomBlock;
import github.flandre.examplemod.common.block.ExampleBlock;
import github.flandre.examplemod.common.block.ExampleDirectionBlock;
import github.flandre.examplemod.common.block.ExampleGuiBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);

    public static final RegistryObject<ExampleBlock> EXAMPLE_BLOCK = BLOCKS.register("example_block",ExampleBlock::new);

    public static final  RegistryObject<ExampleDirectionBlock> EXAMPLE_DIRECTION_BLOCK = BLOCKS.register("example_direction_block",ExampleDirectionBlock::new);

    public static final RegistryObject<CustomBlock> CUSTOM_BLOCK = BLOCKS.register("custom_block",CustomBlock::new);

    public static final RegistryObject<Block> EXAMPLE_GUI_BLOCK = BLOCKS.register("example_gui_block", ExampleGuiBlock::new);
}
