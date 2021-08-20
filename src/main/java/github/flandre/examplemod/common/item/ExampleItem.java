package github.flandre.examplemod.common.item;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.item.Item;

public class ExampleItem extends Item {
    public ExampleItem() {
        super(new Properties().group(ExampleGroup.exampleGroup));
    }
}
