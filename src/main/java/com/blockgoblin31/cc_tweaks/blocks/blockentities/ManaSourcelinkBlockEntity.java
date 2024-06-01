package com.blockgoblin31.cc_tweaks.blocks.blockentities;

import com.hollingsworth.arsnouveau.common.block.tile.SourcelinkTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.mana.ManaReceiver;

public class ManaSourcelinkBlockEntity extends SourcelinkTile implements ManaReceiver {
    public ManaSourcelinkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MANA_SOURCE_LINK, pos, state);
    }

    @Override
    public Level getManaReceiverLevel() {
        return this.getLevel();
    }

    @Override
    public BlockPos getManaReceiverPos() {
        return this.getBlockPos();
    }

    @Override
    public int getCurrentMana() {
        return getSource();
    }

    @Override
    public boolean isFull() {
        return !canAcceptSource();
    }

    @Override
    public void receiveMana(int mana) {
        addSource(mana);
    }

    @Override
    public boolean canReceiveManaFromBursts() {
        return canAcceptSource();
    }
}
