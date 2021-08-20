package github.flandre.examplemod.common.entity.render;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.entity.ExampleEntity;
import github.flandre.examplemod.common.entity.model.ExampleEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ExampleEntityRender extends MobRenderer<ExampleEntity, ExampleEntityModel> {
    private static final ResourceLocation EXAMPLE_TEXTURES_1 = new ResourceLocation(ExampleMod.MODID,"textures/entity/example_entity.png");
    private static final ResourceLocation EXAMPLE_TEXTURES_2 = new ResourceLocation(ExampleMod.MODID,"textures/entity/example_entity_2.png");
    private static final ResourceLocation EXAMPLE_TEXTURES_3 = new ResourceLocation(ExampleMod.MODID,"textures/entity/example_entity_3.png");

    public ExampleEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ExampleEntityModel(),0.8f);
    }

    @Override
    public ResourceLocation getEntityTexture(ExampleEntity entity) {
        byte color = entity.getCOLOR();
        if(color == 2 ){
            return EXAMPLE_TEXTURES_3;
        }else if(color == 1 ){
            return EXAMPLE_TEXTURES_2;
        }else {
            return EXAMPLE_TEXTURES_1;
        }
    }
}
