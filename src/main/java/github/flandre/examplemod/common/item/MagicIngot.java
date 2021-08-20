package github.flandre.examplemod.common.item;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.item.Item;

public class MagicIngot extends Item {
    public MagicIngot() {
        super(new Properties().group(ExampleGroup.exampleGroup));
    }
}
