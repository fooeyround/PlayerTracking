package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class TrackiniumOreBlock extends ExperienceDroppingBlock implements PolymerBlock {
    public TrackiniumOreBlock(Settings settings) {
        super(settings, UniformIntProvider.create(3, 7));
    }

    @Override
    public String getTranslationKey() {
        return "Trackinium Ore";
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.STRUCTURE_BLOCK;
    }
}
