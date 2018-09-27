package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.CategoryCommand;
import guru.springframework.recipeapp.command.IngredientCommand;
import guru.springframework.recipeapp.command.NotesCommand;
import guru.springframework.recipeapp.command.RecipeCommand;
import guru.springframework.recipeapp.model.Category;
import guru.springframework.recipeapp.model.Difficulty;
import guru.springframework.recipeapp.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private final Long ID = 1L;
    private final Long ID_1 = 10L;
    private final Long ID_2 = 20L;
    private final String DESCRIPTION = "description";
    private final Integer PREP_TIME = 10;
    private final Integer COOK_TIME = 20;
    private final Integer SERVINGS = 4;
    private final String SOURCE = "source";
    private final String URL = "url";
    private final String DIRECTIONS = "directions";
    private final Difficulty DIFFICULTY = Difficulty.EASY;

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory());
    }

    @Test
    public void testConvert_NullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvert_EmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void testConvert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setNotes(new NotesCommand());

        recipeCommand.setIngredients(new HashSet<>(Arrays.asList(getIngredientCommand(ID_1), getIngredientCommand(ID_2))));
        recipeCommand.setCategories(new HashSet<>(Arrays.asList(getCategoryCommand(ID_1), getCategoryCommand(ID_2))));

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }

    @Test
    public void testConvert_WithNullCategories(){
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setNotes(new NotesCommand());
        recipeCommand.setIngredients(new HashSet<>(Arrays.asList(getIngredientCommand(ID_1), getIngredientCommand(ID_2))));

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(0, recipe.getCategories().size());
    }

    @Test
    public void testConvert_WithNullIngredients(){
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setNotes(new NotesCommand());
        recipeCommand.setCategories(new HashSet<>(Arrays.asList(getCategoryCommand(ID_1), getCategoryCommand(ID_2))));

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(0, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }

    private IngredientCommand getIngredientCommand(Long id){
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(id);
        return ingredientCommand;
    }

    private CategoryCommand getCategoryCommand(Long id){
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(id);
        return categoryCommand;
    }
}