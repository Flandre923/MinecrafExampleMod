package github.flandre.examplemod.common.item.food;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ExampleFood extends Item {
    private static final Food food = (new Food.Builder())
            .saturation(5)  // 饱腹度
            .hunger(10) // 鸡腿
            // （药水效果实例（药水效果，持续时间，等级 [0 一级  1 二级。。]），触发概率 [1 -》 100%]）
            .effect(()-> new EffectInstance(Effects.LUCK,3*20,1),1)
            .setAlwaysEdible() // 一直能吃  满饱腹也能吃
            .build();

    public ExampleFood() {
        super(new Properties().group(ExampleGroup.exampleGroup).food(food));
    }

}
