package guru.springframework.recipeapp.service;

import guru.springframework.recipeapp.model.Recipe;
import guru.springframework.recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipesTest() {
        //given
        Recipe recipe = new Recipe();
        List<Recipe> recipesData = Collections.singletonList(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        //when
        List<Recipe> recipes = recipeService.getRecipes();

        //then
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest(){
        //given
        long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        //when
        Recipe actualRecipe = recipeService.findById(id);

        //then
        assertNotNull("Null recipe returned", actualRecipe);
        verify(recipeRepository, times(1)).findById(id);
    }
}