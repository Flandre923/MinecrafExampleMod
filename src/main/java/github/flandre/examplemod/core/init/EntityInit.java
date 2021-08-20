package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.entity.ExampleEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExampleMod.MODID);

    public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE_ENTITY =
            ENTITIES.register("example_entity",
                    ()->ExampleEntity.TYPE);
}
