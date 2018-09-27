package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.RecipeCommand;
import guru.springframework.recipeapp.model.Category;
import guru.springframework.recipeapp.model.Difficulty;
import guru.springframework.recipeapp.model.Ingredient;
import guru.springframework.recipeapp.model.Notes;
import guru.springframework.recipeapp.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

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

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());
    }

    @Test
    public void testConvert_NullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvert_EmptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void testConvert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());

        recipe.setIngredients(new HashSet<>(Arrays.asList(getIngredient(ID_1), getIngredient(ID_2))));
        recipe.setCategories(new HashSet<>(Arrays.asList(getCategory(ID_1), getCategory(ID_2))));

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());

    }

    @Test
    public void testConvert_WithNullCategories(){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());
        recipe.setIngredients(new HashSet<>(Arrays.asList(getIngredient(ID_1), getIngredient(ID_2))));

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(0, recipeCommand.getCategories().size());
    }

    @Test
    public void testConvert_WithNullIngredients(){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());

        recipe.setCategories(new HashSet<>(Arrays.asList(getCategory(ID_1), getCategory(ID_2))));

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(0, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());
    }

    private Ingredient getIngredient(Long id){
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        return ingredient;
    }

    private Category getCategory(Long id){
        Category category = new Category();
        category.setId(id);
        return category;
    }
}