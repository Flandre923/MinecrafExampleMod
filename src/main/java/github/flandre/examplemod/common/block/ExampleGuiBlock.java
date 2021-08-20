package github.flandre.examplemod.common.block;

import github.flandre.examplemod.common.tileentity.ExampleGuiTileEntity;
import github.flandre.examplemod.core.init.TileEntityInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import sun.net.NetHooks;

import javax.annotation.Nullable;

public class ExampleGuiBlock extends Block {
    public ExampleGuiBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(10,10));

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.EXAMPLE_GUI_TILE_ENTITY.get().create();
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof ExampleGuiTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity) player,(ExampleGuiTileEntity) tileEntity,pos);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
