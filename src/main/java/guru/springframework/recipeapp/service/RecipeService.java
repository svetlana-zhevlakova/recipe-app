package guru.springframework.recipeapp.service;

import guru.springframework.recipeapp.command.RecipeCommand;
import guru.springframework.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);
}
