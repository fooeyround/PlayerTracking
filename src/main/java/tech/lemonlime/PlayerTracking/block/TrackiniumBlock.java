package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;

public class TrackiniumBlock extends Block implements PolymerTexturedBlock {


    public final BlockState modelState = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(new Identifier(PlayerTracking.MODID,"trackinium")));

    public TrackiniumBlock(AbstractBlock.Settings settings) {
        super(settings);

    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.STRUCTURE_BLOCK;
    }


//    @Deprecated
//    @Override
//    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
//
//        return Collections.singletonList((new ItemStack(ModItems.TRACKINIUM_BLOCK)));
//    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        if (PolymerBlockResourceUtils.getBlocksLeft(BlockModelType.FULL_BLOCK) == 0) return PolymerTexturedBlock.super.getPolymerBlockState(state);
        return modelState;
    }



}
