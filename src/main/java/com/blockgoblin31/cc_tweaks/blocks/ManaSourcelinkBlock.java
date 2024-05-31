package com.blockgoblin31.cc_tweaks.blocks;

import com.blockgoblin31.cc_tweaks.blocks.blockentities.ManaSourcelinkBlockEntity;
import com.hollingsworth.arsnouveau.common.block.SourcelinkBlock;
import com.hollingsworth.arsnouveau.common.block.TickableModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ManaSourcelinkBlock extends SourcelinkBlock {
    public ManaSourcelinkBlock() {
        super(TickableModBlock.defaultProperties().noOcclusion());
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ManaSourcelinkBlockEntity(pPos, pState);
    }
}
