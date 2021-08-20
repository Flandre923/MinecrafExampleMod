package github.flandre.examplemod.common.item;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.client.gui.ExampleSimpleGui;
import github.flandre.examplemod.client.gui.OpenGui;
import github.flandre.examplemod.core.init.itemgroup.ExampleGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class GuiItem extends Item {
    public GuiItem() {
        super(new Properties().group(ExampleGroup.exampleGroup));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(worldIn.isRemote){
            DistExecutor.safeCallWhenOn(Dist.CLIENT,() -> OpenGui::new);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
