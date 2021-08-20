package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.tileentity.ExampleGuiTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExampleMod.MODID);

    public static final RegistryObject<TileEntityType<ExampleGuiTileEntity>> EXAMPLE_GUI_TILE_ENTITY =
            TILE_ENTITY_TYPES.register("example_gui_tile_entity",()-> TileEntityType.Builder.create(ExampleGuiTileEntity::new,BlockInit.EXAMPLE_GUI_BLOCK.get()).build(null));


}
