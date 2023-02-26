package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import tech.lemonlime.PlayerTracking.PlayerTracking;

public class TrackiniumOreBlock extends ExperienceDroppingBlock implements PolymerTexturedBlock {
    public TrackiniumOreBlock(Settings settings) {
        super(settings, UniformIntProvider.create(3, 7));
    }


    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.STRUCTURE_BLOCK;
    }


    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        if (PolymerBlockResourceUtils.getBlocksLeft(BlockModelType.FULL_BLOCK) < 5) return PolymerTexturedBlock.super.getPolymerBlockState(state);;
        return PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(new Identifier(PlayerTracking.MODID,"trackinium_ore")));
    }
}
