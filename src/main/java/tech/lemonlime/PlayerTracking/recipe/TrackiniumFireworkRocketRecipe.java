package tech.lemonlime.PlayerTracking.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import tech.lemonlime.PlayerTracking.registry.ModItems;

public class TrackiniumFireworkRocketRecipe extends SpecialCraftingRecipe {

    private static final Ingredient PAPER;

    private static final Ingredient TRACKINIUM_INGOT;
    private static final Ingredient DURATION_MODIFIER;
    private static final Ingredient FIREWORK_STAR;

    public TrackiniumFireworkRocketRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean hasPaper = false;
        boolean hasTrackiniumIngot = false;
        int i = 0;

        for(int j = 0; j < craftingInventory.size(); ++j) {
            ItemStack itemStack = craftingInventory.getStack(j);
            if (!itemStack.isEmpty()) {
                if (PAPER.test(itemStack)) {
                    if (hasPaper) {
                        return false;
                    }

                    hasPaper = true;
                } else if (TRACKINIUM_INGOT.test(itemStack)) {
                    if (hasTrackiniumIngot) {
                        return false;
                    }

                    hasTrackiniumIngot = true;
                } else if (DURATION_MODIFIER.test(itemStack)) {
                    ++i;
                    if (i > 3) {
                        return false;
                    }
                } else if (!FIREWORK_STAR.test(itemStack)) {
                    return false;
                }
            }
        }

        return hasTrackiniumIngot && hasPaper && i >= 1;
    }




    public ItemStack craft(CraftingInventory craftingInventory,DynamicRegistryManager dynamicRegistryManager) {
        ItemStack itemStack = new ItemStack(Items.FIREWORK_ROCKET, 3);
        NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("Fireworks");
        NbtList nbtList = new NbtList();
        int i = 0;

        for(int j = 0; j < craftingInventory.size(); ++j) {
            ItemStack itemStack2 = craftingInventory.getStack(j);
            if (!itemStack2.isEmpty()) {
                if (DURATION_MODIFIER.test(itemStack2)) {
                    ++i;
                } else if (FIREWORK_STAR.test(itemStack2)) {
                    NbtCompound nbtCompound2 = itemStack2.getSubNbt("Explosion");
                    if (nbtCompound2 != null) {
                        nbtList.add(nbtCompound2);
                    }
                }
            }
        }

        nbtCompound.putByte("Flight", (byte)i);
        if (!nbtList.isEmpty()) {
            nbtCompound.put("Explosions", nbtList);
        }

        return itemStack;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getOutput(DynamicRegistryManager dynamicRegistryManager) {
        return new ItemStack(Items.FIREWORK_ROCKET);
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.FIREWORK_ROCKET;
    }

    static {
        PAPER = Ingredient.ofItems(Items.PAPER);
        TRACKINIUM_INGOT = Ingredient.ofItems(ModItems.TRACKINIUM_INGOT);

        DURATION_MODIFIER = Ingredient.ofItems(Items.GUNPOWDER);
        FIREWORK_STAR = Ingredient.ofItems(Items.FIREWORK_STAR);
    }
}
