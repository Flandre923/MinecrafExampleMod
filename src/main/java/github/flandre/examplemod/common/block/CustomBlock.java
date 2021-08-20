package github.flandre.examplemod.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

public class CustomBlock extends BaseBlock{
    private static final VoxelShape SHAPES = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
            Block.makeCuboidShape(0, 4, 0, 16, 8, 13),
            Block.makeCuboidShape(0, 8, 0, 16, 12, 10),
            Block.makeCuboidShape(0, 12, 0, 16, 16, 6)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public CustomBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(10,10));
        runCaluction(SHAPES);
    }

    public void runCaluction(VoxelShape voxelShape){
        for(Direction direction : Direction.values()){
            calulateShape(direction,voxelShape);
        }
    }
}
