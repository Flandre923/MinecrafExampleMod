package github.flandre.examplemod.common.item.tools;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class ExampleSword extends SwordItem {
    public ExampleSword() {
        super(ExampleTier.EXAMPLETIER, 10, -2.4f, new Properties().group(ExampleGroup.exampleGroup));
    }
}
