package github.flandre.examplemod.common.entity;


import github.flandre.examplemod.core.init.EntityInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExampleEntity extends CreatureEntity {
    //对应实体
    public static final EntityType TYPE = EntityType.Builder.<ExampleEntity>create(ExampleEntity::new, EntityClassification.MONSTER).size(1.2f,1.95f).build("example_entity");
    // 存储颜色的字段
    private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(ExampleEntity.class, DataSerializers.BYTE);

    public ExampleEntity(World worldIn){
        this(EntityInit.EXAMPLE_ENTITY.get(),worldIn);
    }

    public ExampleEntity(EntityType<ExampleEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public byte getCOLOR() {
        return this.getDataManager().get(COLOR);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(COLOR,(byte)0);
    }

    public static AttributeModifierMap.MutableAttribute func_234301_m_() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        //生物初始化  出生时候
        this.getDataManager().set(COLOR,(byte)this.rand.nextInt(3));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0,new SwimGoal(this));
        this.goalSelector.addGoal(1,new MeleeAttackGoal(this,1.0,false));
        this.goalSelector.addGoal(2,new WaterAvoidingRandomWalkingGoal(this,0.8));
        this.goalSelector.addGoal(3,new ChangGrassToDirt(this));
        this.goalSelector.addGoal(4,new LookAtGoal(this, PlayerEntity.class,8));
        this.goalSelector.addGoal(5,new LookRandomlyGoal(this));

        this.targetSelector.addGoal(0,new HurtByTargetGoal(this));
    }

    private static class ChangGrassToDirt extends Goal{
        private final ExampleEntity entity;

        public ChangGrassToDirt(ExampleEntity entity){
            this.entity = entity;
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            BlockPos blockPos = new BlockPos(entity.getPosX(),entity.getPosY()-0.2,entity.getPosZ());
            this.entity.world.setBlockState(blockPos,Blocks.DIRT.getDefaultState());
        }

        @Override
        public boolean shouldExecute() {
            BlockPos blockPos = new BlockPos(entity.getPosX(),entity.getPosY()-0.2,entity.getPosZ());
            return this.entity.world.getBlockState(blockPos).getBlock() == Blocks.GRASS_BLOCK;
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte("color",this.getDataManager().get(COLOR));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.getDataManager().set(COLOR,compound.getByte("color"));
    }
}
