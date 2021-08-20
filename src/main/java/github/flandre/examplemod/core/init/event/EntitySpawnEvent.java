package github.flandre.examplemod.core.init.event;

import github.flandre.examplemod.common.entity.ExampleEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class EntitySpawnEvent {
    public static void BiomeLoading(BiomeLoadingEvent event){
        if( event.getName() == null){
            return;
        }
        MobSpawnInfoBuilder spawns = event.getSpawns();

        if(event.getCategory().equals(Biome.Category.NETHER)){
            spawns.withSpawner(EntityClassification.MONSTER,new MobSpawnInfo.Spawners(ExampleEntity.TYPE,10,4,4));
        }

    }
}
