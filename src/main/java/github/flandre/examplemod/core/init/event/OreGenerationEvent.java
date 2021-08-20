package github.flandre.examplemod.core.init.event;

import github.flandre.examplemod.core.init.BlockInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class OreGenerationEvent {
    public static void generateOres(final BiomeLoadingEvent event){
        if(! (event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))){
            BiomeGenerationSettingsBuilder generation = event.getGeneration();
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(
                                    OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                    BlockInit.EXAMPLE_BLOCK.get().getDefaultState(),
                                    5)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(15,0,64)))
                            .square().func_242731_b(20));
        }
    }
}
