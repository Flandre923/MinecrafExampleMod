package github.flandre.examplemod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.container.ExampleGuiContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) // bug
public class ExampleGUI extends ContainerScreen<ExampleGuiContainer> {

    private static final ResourceLocation EXAMPLE_GUI = new ResourceLocation(ExampleMod.MODID,"/textures/gui/display_case1.png");

    public ExampleGUI(ExampleGuiContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft=0;
        this.guiTop=0;
        this.xSize=176;
        this.ySize=176;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.font.func_243248_b(matrixStack,this.playerInventory.getDisplayName(),(float) this.playerInventoryTitleX,(float) this.playerInventoryTitleY,4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.textureManager.bindTexture(EXAMPLE_GUI);

        int x = (this.width - this.xSize) /2;
        int y = (this.height -this.ySize)/2;

        this.blit(matrixStack,x,y,0,0,this.xSize,this.ySize);
        int barh = 16;
        int barw = Math.round(this.getContainer().data.get(0) *0.35f);
        this.blit(matrixStack,x+44,y+59,0,176,barw,barh);

    }
}
