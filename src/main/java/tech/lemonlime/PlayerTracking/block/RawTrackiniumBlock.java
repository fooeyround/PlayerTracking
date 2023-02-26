package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.registry.ModItems;

import java.util.Collections;
import java.util.List;

public class RawTrackiniumBlock extends Block implements PolymerTexturedBlock {


    public RawTrackiniumBlock(Settings settings) {
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
//        return Collections.singletonList((new ItemStack(ModItems.RAW_TRACKINIUM_BLOCK)));
//    }


    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        if (PolymerBlockResourceUtils.getBlocksLeft(BlockModelType.FULL_BLOCK) < 5) return state;
        return PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(new Identifier(PlayerTracking.MODID,"raw_trackinium_block")));
    }

}
