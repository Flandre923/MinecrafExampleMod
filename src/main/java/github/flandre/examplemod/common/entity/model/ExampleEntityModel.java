package github.flandre.examplemod.common.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.flandre.examplemod.common.entity.ExampleEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ExampleEntityModel extends EntityModel<ExampleEntity> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer rightleg;
    private final ModelRenderer leftleg;

    public ExampleEntityModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(0, 32).addBox(-7.0F, -34.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-9.0F, -20.0F, -9.0F, 18.0F, 14.0F, 18.0F, 0.0F, false);

        rightleg = new ModelRenderer(this);
        rightleg.setRotationPoint(0.0F, 24.0F, 0.0F);
        rightleg.setTextureOffset(54, 0).addBox(-5.0F, -6.0F, -5.0F, 4.0F, 6.0F, 12.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftleg.setTextureOffset(44, 48).addBox(1.0F, -6.0F, -5.0F, 4.0F, 6.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(ExampleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
        leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
