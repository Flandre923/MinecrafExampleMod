package github.flandre.examplemod.client.gui;

import github.flandre.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGui {
    public OpenGui(){
        // 显示GUI 第二参数是GUI 的标题
        Minecraft.getInstance().displayGuiScreen(new ExampleSimpleGui(new TranslationTextComponent(ExampleMod.MODID + ".test")));
    }

}
