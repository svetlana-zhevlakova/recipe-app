package guru.springframework.recipeapp.service;

import guru.springframework.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();
}
