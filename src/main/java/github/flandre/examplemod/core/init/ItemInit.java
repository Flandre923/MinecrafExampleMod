package github.flandre.examplemod.core.init;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.entity.ExampleEntity;
import github.flandre.examplemod.common.item.ExampleItem;
import github.flandre.examplemod.common.item.GuiItem;
import github.flandre.examplemod.common.item.InfiniteSnowball;
import github.flandre.examplemod.common.item.MagicIngot;
import github.flandre.examplemod.common.item.armor.ExampleArmorMaterial;
import github.flandre.examplemod.common.item.armor.ExampleHelmet;
import github.flandre.examplemod.common.item.food.ExampleFood;
import github.flandre.examplemod.common.item.tools.ExampleAxe;
import github.flandre.examplemod.common.item.tools.ExampleSword;
import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    //对象
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);
    // 物品
    public static final RegistryObject<ExampleItem> EXAMPLE_ITEM =
            ITEMS.register("example_item",ExampleItem::new);
    public static final RegistryObject<MagicIngot> MAGIC_INGOT =
            ITEMS.register("magic_ingot",MagicIngot::new);
    public static final RegistryObject<InfiniteSnowball> INFINITE_SNOWBALL=
            ITEMS.register("infine_snowball",InfiniteSnowball::new);
    // 剑
    public static final RegistryObject<ExampleSword> EXAMPLE_SWORD =
            ITEMS.register("example_sword",ExampleSword::new);
    // 斧头
    public static final RegistryObject<ExampleAxe> EXAMEPLE_AXE =
            ITEMS.register("example_axe",ExampleAxe::new);
    // 盔甲头
    public static final RegistryObject<ExampleHelmet> EXAMPLE_HELMET =
            ITEMS.register("example_helmet",ExampleHelmet::new);
    // 盔甲
    public static final RegistryObject<Item> EXAMPLE_CHEST =
            ITEMS.register("example_chestplate",
                    ()->new ArmorItem(ExampleArmorMaterial.EXAMPLE,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().group(ExampleGroup.exampleGroup)));
    public static final RegistryObject<Item> EXAMPLE_LEGGING =
            ITEMS.register("example_leggings",()->new ArmorItem(ExampleArmorMaterial.EXAMPLE,
                    EquipmentSlotType.LEGS,new Item.Properties().group(ExampleGroup.exampleGroup)));
    public static final RegistryObject<Item> EXAMPLE_FEET =
            ITEMS.register("example_boots",()->new ArmorItem(ExampleArmorMaterial.EXAMPLE,EquipmentSlotType.FEET,
                    new Item.Properties().group(ExampleGroup.exampleGroup)));

    // 食物
    public static final RegistryObject<ExampleFood> EXAMPLE_FOOD =
            ITEMS.register("example_food",ExampleFood::new);
    //方块物品
    public static final RegistryObject<Item> EXAMPLE_BLCOK =
            ITEMS.register("example_block",()->new BlockItem(BlockInit.EXAMPLE_BLOCK.get(),new Item.Properties().group(ExampleGroup.exampleGroup)));
    public static final RegistryObject<Item> EXAMPLE_DIRECTION_BLOCK =
            ITEMS.register("example_direction_block",()->new BlockItem(BlockInit.EXAMPLE_DIRECTION_BLOCK.get(),new Item.Properties().group(ExampleGroup.exampleGroup)));
    public static final RegistryObject<Item> CUSTOM_BLOCK =
            ITEMS.register("custom_block",()->new BlockItem(BlockInit.CUSTOM_BLOCK.get(),new Item.Properties().group(ExampleGroup.exampleGroup)));

    // 刷怪蛋
    public static final RegistryObject<SpawnEggItem> EXAMPLE_ENTITY_EGG =
            ITEMS.register("example_entity_egg",()->new SpawnEggItem(ExampleEntity.TYPE,803406, 11013646,new Item.Properties().group(ExampleGroup.exampleGroup)));

    // GUI方块
    public static final RegistryObject<Item> GUI_ITEM =
            ITEMS.register("gui_item", GuiItem::new);

    public static final  RegistryObject<Item> EXAMPLE_GUI_ITEM =
            ITEMS.register("example_gui_item",()->new BlockItem(BlockInit.EXAMPLE_GUI_BLOCK.get(),new Item.Properties().group(ExampleGroup.exampleGroup)));
}
