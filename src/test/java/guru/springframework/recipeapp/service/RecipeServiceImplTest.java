package guru.springframework.recipeapp.service;

import guru.springframework.recipeapp.command.RecipeCommand;
import guru.springframework.recipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.recipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.recipeapp.model.Recipe;
import guru.springframework.recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    private final Long ID = 1L;

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
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

    @Test
    public void testSaveRecipeCommand(){
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        //when
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        //then
        assertEquals(ID, savedRecipeCommand.getId());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(recipeCommandToRecipe, times(1)).convert(recipeCommand);
        verify(recipeToRecipeCommand, times(1)).convert(any(Recipe.class));
    }

    @Test
    public void testFindCommandById(){
        //given
        long id = 1L;

        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);
        when(recipeToRecipeCommand.convert(optionalRecipe.get())).thenReturn(recipeCommand);

        //when
        RecipeCommand command = recipeService.findCommandById(id);

        //then
        assertEquals(new Long(id), command.getId());
        verify(recipeRepository, times(1)).findById(id);
        verify(recipeToRecipeCommand, times(1)).convert(any(Recipe.class));
    }
}