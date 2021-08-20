package github.flandre.examplemod.core.init.itemgroup;

import github.flandre.examplemod.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleGroup extends ItemGroup {

    public static final ExampleGroup exampleGroup = new ExampleGroup();

    public ExampleGroup() {
        super("example_group");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
    }
}
