package github.flandre.examplemod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.flandre.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class ExampleHUD extends AbstractGui {
    private static final ResourceLocation TEXTURES = new ResourceLocation(ExampleMod.MODID,"textures/gui/example_gui.png");

    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;

    public ExampleHUD(MatrixStack matrixStack) {
        this.width = Minecraft.getInstance().getMainWindow().getWidth();
        this.height = Minecraft.getInstance().getMainWindow().getHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }

    public void setMatrixStack(MatrixStack matrixStack) {
        this.matrixStack = matrixStack;
    }

    public void render(float power){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
        blit(this.matrixStack,0,0,0,9,80,40);

        blit(this.matrixStack,0,15,power<4?0:9,0,9,9);

        drawCenteredString(this.matrixStack,this.minecraft.fontRenderer,"EXAMPLE_POWER:"+power,15,15,0xffffff);
    }

}
