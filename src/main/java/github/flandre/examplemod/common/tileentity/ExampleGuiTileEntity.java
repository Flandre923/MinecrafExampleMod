package github.flandre.examplemod.common.tileentity;

import github.flandre.examplemod.ExampleMod;
import github.flandre.examplemod.common.container.ExampleGuiContainer;
import github.flandre.examplemod.core.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExampleGuiTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public static final int SLOTS = 3;
    public int compressorProgress = 0;
    public final IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            return ExampleGuiTileEntity.this.compressorProgress;
        }

        @Override
        public void set(int index, int value) {
            ExampleGuiTileEntity.this.compressorProgress = value;
        }

        @Override
        public int size() {
            return 1;
        }
    };


    private final ItemStackHandler UP = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            ExampleGuiTileEntity.this.markDirty();
        }
    };

    private final ItemStackHandler SIDE = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            ExampleGuiTileEntity.this.markDirty();
        }
    };
    private final ItemStackHandler DOWN = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            ExampleGuiTileEntity.this.markDirty();
        }
    };

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.DOWN.deserializeNBT(nbt.getCompound("DOWN"));
        this.SIDE.deserializeNBT(nbt.getCompound("SIDE"));
        this.UP.deserializeNBT(nbt.getCompound("UP"));
        this.compressorProgress = nbt.getInt("progress");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("DOWN",this.DOWN.serializeNBT());
        compound.put("SIDE",this.SIDE.serializeNBT());
        compound.put("UP",this.UP.serializeNBT());
        compound.putInt("progress",this.compressorProgress);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        if(cap.equals(itemHandlerCapability)){
            if(Direction.UP.equals(side)){
                return LazyOptional.of(()->this.UP).cast();
            }
            if (Direction.DOWN.equals(side)){
                return LazyOptional.of(()->this.DOWN).cast();
            }
            return LazyOptional.of(()->this.SIDE).cast();
        }
        return super.getCapability(cap, side);
    }

    public ExampleGuiTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ExampleGuiTileEntity(){
        this(TileEntityInit.EXAMPLE_GUI_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        Item dirt = Items.DIRT;
        if(dirt.equals(this.SIDE.extractItem(0,1,true).getItem())){
            if(compressorProgress %20 ==0){
                if(this.UP.extractItem(0,1,true).getItem().equals(Items.SNOWBALL)){// bug
                    this.UP.extractItem(0,1,false);
                    this.compressorProgress =this.compressorProgress+1;

                }
            }else{
                this.compressorProgress += 1;
                if(compressorProgress>=240){
                    if(this.DOWN.insertItem(0,new ItemStack(Items.DIAMOND),true).isEmpty()){
                        this.DOWN.insertItem(0,new ItemStack(Items.DIAMOND),false);
                        this.SIDE.extractItem(0,1,false);
                        this.compressorProgress = 0;
                    }else{
                        compressorProgress -= 1;
                    }
                }else{
                    markDirty();
                }
            }
        }else if(this.compressorProgress>0){
            this.compressorProgress = 0;
            markDirty();
        }
    }


    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container."+ ExampleMod.MODID+".example_gui_block");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ExampleGuiContainer(id,playerInventory,this,data);
    }
}
