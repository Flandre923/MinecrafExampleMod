package github.flandre.examplemod.common.item.armor;

import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class ExampleHelmet extends ArmorItem {
    public ExampleHelmet(){
        super(ExampleArmorMaterial.EXAMPLE, EquipmentSlotType.HEAD,new Properties().group(ExampleGroup.exampleGroup));
    }
}
