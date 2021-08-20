package github.flandre.examplemod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.apache.logging.log4j.core.config.Order;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BaseBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction,VoxelShape>();

    public BaseBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING,Direction.NORTH));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING,context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context){
        return SHAPES.get(state.get(FACING));
    }

    public static void calulateShape(Direction to,VoxelShape shape){
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};
        int items = (to.getHorizontalIndex() - Direction.NORTH.getHorizontalIndex() + 4)%4;


        for(int i =0;i<items;i++){
            buffer[0].forEachBox((minX,minY,minZ,maxX,maxY,maxZ)->buffer[1]=VoxelShapes.or(buffer[1],VoxelShapes.create(1-maxZ,minY,minX,1-minZ,maxY,maxX)));
            buffer[0]=buffer[1];
            buffer[1]=VoxelShapes.empty();
        }
        SHAPES.put(to,buffer[0]);
    }
}

