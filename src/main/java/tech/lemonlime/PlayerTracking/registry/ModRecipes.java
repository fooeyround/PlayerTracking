package tech.lemonlime.PlayerTracking.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import tech.lemonlime.PlayerTracking.recipe.TrackiniumFireworkRocketRecipe;

;

public class ModRecipes {



    public static RecipeSerializer<TrackiniumFireworkRocketRecipe> TRACKINIUM_FIREWORK_ROCKET = RecipeSerializer.register("crafting_special_trackinium_firework_rocket", new SpecialRecipeSerializer(TrackiniumFireworkRocketRecipe::new));






    public static void register() {

    }




}
