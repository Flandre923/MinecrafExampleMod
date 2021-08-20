package github.flandre.examplemod.client.gui;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.flandre.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExampleSimpleGui extends Screen {
    TextFieldWidget textFieldWidget;
    Button button;
    ResourceLocation SIMPLE_TEXTURES = new ResourceLocation(ExampleMod.MODID,"textures/gui/xxx.png");
    TranslationTextComponent content = new TranslationTextComponent("example.simple.gui");

    SliderPercentageOption option;
    Widget sliderBar;

    public ExampleSimpleGui(ITextComponent titleIn) {
        super(titleIn);
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bindTexture(SIMPLE_TEXTURES);
        int textureWidth  = 208;
        int textureHeight = 156;

        this.blit(matrixStack,this.width/2-150,10,0,0,300,200,textureWidth,textureHeight);
        drawCenteredString(matrixStack,this.font,content,this.width/2-10,30,0xeb0505);

        this.textFieldWidget.render(matrixStack,mouseX,mouseY,partialTicks);
        this.button.render(matrixStack,mouseX,mouseY,partialTicks);
        this.sliderBar.render(matrixStack,mouseX,mouseY,partialTicks);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    @Override
    public void init() {
        this.minecraft.keyboardListener.enableRepeatEvents(true);

        this.textFieldWidget = new TextFieldWidget(this.font,this.width/2-100,66,200,20,new TranslationTextComponent("gui."+ExampleMod.MODID+".first.context"));
        this.button = new Button(this.width / 2 - 40,96,80,20,new TranslationTextComponent("gui." + ExampleMod.MODID + ".first.button.test"),(button)->{});
        this.addButton(button);

        this.option = new SliderPercentageOption(ExampleMod.MODID+".sliderbar",5,100,5,gameSettings -> Double.valueOf(0),(settings,value)->{},(gamesttings,sliderPercentageOption)->new TranslationTextComponent("gui."+ExampleMod.MODID+".first.test"));

        this.sliderBar = this.option.createWidget(Minecraft.getInstance().gameSettings,this.width/2-100,120,200);
        this.children.add(this.sliderBar);
        super.init();

    }
}
