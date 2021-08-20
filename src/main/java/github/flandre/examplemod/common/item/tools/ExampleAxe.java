package github.flandre.examplemod.common.item.tools;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class ExampleAxe extends AxeItem {
    public ExampleAxe() {
        super(ExampleTier.EXAMPLETIER,15,-2f,new Properties().group(ExampleGroup.exampleGroup));
    }
}
