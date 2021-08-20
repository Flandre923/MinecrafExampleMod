package github.flandre.examplemod.common.container;

import github.flandre.examplemod.common.tileentity.ExampleGuiTileEntity;
import github.flandre.examplemod.core.init.BlockInit;
import github.flandre.examplemod.core.init.ContainerInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.Objects;


public class ExampleGuiContainer extends Container {
    public final ExampleGuiTileEntity tileEntity;

    private final IWorldPosCallable ableToInteract;

    private final LazyOptional<IItemHandler> UP;
    private final LazyOptional<IItemHandler> DOWN;
    private final LazyOptional<IItemHandler> SIDE;

    public final IIntArray data;
    public ExampleGuiContainer(final int id, final PlayerInventory playerInventory, final ExampleGuiTileEntity entity, IIntArray data){
        super(ContainerInit.EXAMPLE_GUI_CONTIANER.get(),id); // bug
        this.tileEntity = entity;
        this.ableToInteract = IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()),tileEntity.getPos());

        this.data =data;
        this.trackIntArray(data);
        this.UP = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
        this.DOWN = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN);
        this.SIDE = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.NORTH);

        this.UP.ifPresent((s)->{
            this.addSlot(new SlotItemHandler(s,0,80,32));
        });
        this.DOWN.ifPresent((s)->{
            this.addSlot(new SlotItemHandler(s,0,134,59));//bug
        });
        this.SIDE.ifPresent((s)->{
            this.addSlot(new SlotItemHandler(s,0,26,59));
        });


        for(int i =0;i<9;i++){
            this.addSlot(new Slot(playerInventory,i,i*18+8,152));
            this.addSlot(new Slot(playerInventory,i+9,i*18+8,94));
            this.addSlot(new Slot(playerInventory,i+18,i*18+8,112));
            this.addSlot(new Slot(playerInventory,i+27,i*18+8,130));

        }



    }


    public ExampleGuiContainer(final int id, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(id,playerInventory,getTileEntity(playerInventory,data),new IntArray(1));
    }


    public static ExampleGuiTileEntity getTileEntity(PlayerInventory playerInventory,PacketBuffer data){
        Objects.requireNonNull(playerInventory,"玩家物品栏不能为空");
        Objects.requireNonNull(data,"数据包不能为空");

        BlockPos blockPos = data.readBlockPos();
        TileEntity tileEntity = playerInventory.player.world.getTileEntity(blockPos);

        if(tileEntity instanceof ExampleGuiTileEntity){
            return (ExampleGuiTileEntity) tileEntity;
        }
        throw new IllegalStateException("invalid tile entity");


    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(ableToInteract,playerIn,BlockInit.EXAMPLE_GUI_BLOCK.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
//        ItemStack stack = ItemStack.EMPTY;
//        Slot slot = this.inventorySlots.get(index);
//        if(slot!=null && slot.getHasStack()){
//            ItemStack stack_ = slot.getStack();
//            stack = stack_.copy();
//            if (index<ExampleGuiTileEntity.SLOTS && !this.mergeItemStack(stack_,ExampleGuiTileEntity.SLOTS,this.inventorySlots.size(),true)){
//                return ItemStack.EMPTY;
//            }
//            if(!this.mergeItemStack(stack_,0,ExampleGuiTileEntity.SLOTS,false)){
//                return ItemStack.EMPTY;
//            }
//            if(stack_.isEmpty()){
//                slot.putStack(ItemStack.EMPTY);
//            }else {
//                slot.onSlotChanged();
//            }
//            return stack;
//        }
        return ItemStack.EMPTY;
    }
}
