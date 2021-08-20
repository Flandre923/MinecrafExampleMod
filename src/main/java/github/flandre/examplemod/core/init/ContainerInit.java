package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.container.ExampleGuiContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

public class ContainerInit {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, ExampleMod.MODID);

    public static final RegistryObject<ContainerType<ExampleGuiContainer>> EXAMPLE_GUI_CONTIANER =
            CONTAINER_TYPE.register("example_gui_container", ()->IForgeContainerType.create(ExampleGuiContainer::new));
}
