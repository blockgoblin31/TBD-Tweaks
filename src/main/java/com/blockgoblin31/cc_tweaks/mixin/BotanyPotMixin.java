package com.blockgoblin31.cc_tweaks.mixin;

import com.hollingsworth.arsnouveau.api.source.SourcelinkEventQueue;
import com.hollingsworth.arsnouveau.common.block.tile.AgronomicSourcelinkTile;
import net.darkhax.botanypots.block.BlockEntityBotanyPot;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityBotanyPot.class)
public class BotanyPotMixin {

    @Inject(method = "tickPot(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/darkhax/botanypots/block/BlockEntityBotanyPot;)V",
            remap = false, at=@At("HEAD"))
    private static void cc_tweaks_sourceForPotCrops(Level level, BlockPos pos, BlockState state, BlockEntityBotanyPot pot, CallbackInfo ci) {
        if (pot.getInventory().getRequiredGrowthTime() > 0 && Mth.floor(100f * pot.getGrowthTime() / pot.getInventory().getRequiredGrowthTime()) % 20 == 0 && !pot.isCropHarvestable() && pot.getGrowthTime() != 0 && (Mth.floor(100f * (pot.getGrowthTime() - 1) / pot.getInventory().getRequiredGrowthTime()) % 20 != 0)) {
            SourcelinkEventQueue.addManaEvent(level, AgronomicSourcelinkTile.class, 200, null, pos);
        }
    }
}
