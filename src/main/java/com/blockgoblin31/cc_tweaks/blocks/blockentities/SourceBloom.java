package com.blockgoblin31.cc_tweaks.blocks.blockentities;

import com.blockgoblin31.cc_tweaks.blocks.ModFlowerBlocks;
import com.hollingsworth.arsnouveau.api.source.ISpecialSourceProvider;
import com.hollingsworth.arsnouveau.api.util.SourceUtil;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.client.particle.ParticleLineData;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.entity.EntityFlyingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

public class SourceBloom extends GeneratingFlowerBlockEntity {
    private static final int RANGE = 3;
    private boolean draining;
    private int source = 0;

    public SourceBloom(BlockPos pos, BlockState state) {
        super(ModFlowerBlocks.SOURCEBLOOM, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        if (level == null) return;
        if (level.isClientSide) {
            int baseAge = draining ? 20 : 40;
            int randBound = draining ? 3 : 6;
            int numParticles = draining ? 2 : 1;
            float scaleAge = draining ? (float) ParticleUtil.inRange(0.1, 0.2) : (float) ParticleUtil.inRange(0.05, 0.15);
            if (level.random.nextInt(randBound) == 0 && !Minecraft.getInstance().isPaused()) {
                for (int i = 0; i < numParticles; i++) {
                    Vec3 particlePos = new Vec3(getX(), getY(), getZ()).add(0.5, 0.5, 0.5);
                    particlePos = particlePos.add(ParticleUtil.pointInSphere());
                    level.addParticle(ParticleLineData.createData(new ParticleColor(255, 25, 180), scaleAge, baseAge + level.random.nextInt(20)),
                            particlePos.x(), particlePos.y(), particlePos.z(),
                            getX() + 0.5, getY() + 0.5, getZ() + 0.5);
                }
            }
            return;
        }
        int transferRate = 200;

        if (this.level.getGameTime() % 20 == 0 && this.getSource() < getMaxSource()) {
            ISpecialSourceProvider takePos = SourceUtil.takeSource(worldPosition, level, 3, Math.min(transferRate, this.getSource() - getMaxSource()));
            if (takePos != null) {
                this.addSource(transferRate);
                EntityFlyingItem item = new EntityFlyingItem(level, takePos.getCurrentPos().above(), worldPosition, 255, 50, 80)
                        .withNoTouch();
                item.setDistanceAdjust(2f);
                level.addFreshEntity(item);
                if (!draining) {
                    draining = true;
                    updateBlock();
                }
            } else {
                this.addSource(10);
                if (draining) {
                    draining = false;
                    updateBlock();
                }
            }
        }
        while (this.getSource() >= 10 && this.getMana() <= this.getMaxMana() - 10) {
            this.addMana(10);
            this.addSource(-10);
        }
    }

    //stuff copied over from the InfusionTile to get the tick function to work
    public double getX() {
        return this.worldPosition.getX();
    }
    public double getY() {
        return this.worldPosition.getY();
    }
    public double getZ() {
        return this.worldPosition.getZ();
    }
    private int getMaxSource() {
        return 500;
    }
    public boolean updateBlock() {
        if(level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
            setChanged();
            return true;
        }
        return false;
    }
    private int getSource() {
        return source;
    }
    public int setSource(int source) {
        if (this.source == source)
            return this.source;
        this.source = source;
        if (this.source > this.getMaxSource())
            this.source = this.getMaxSource();
        if (this.source < 0)
            this.source = 0;
        updateBlock();
        return this.source;
    }
    private void addSource(int toAdd) {
        setSource(getSource() + toAdd);
    }

    @Override
    public int getMaxMana() {
        return 500;
    }

    @Override
    public int getColor() {
        return 0x9d4eba;
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
