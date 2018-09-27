package guru.springframework.recipeapp.service;

import guru.springframework.recipeapp.command.RecipeCommand;
import guru.springframework.recipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.recipeapp.model.Recipe;
import guru.springframework.recipeapp.repository.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    private final String NEW_DESCRIPTION = "New Description";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    @Test
    public void testSaveRecipeCommand() {
        //given
        Recipe recipe = recipeRepository.findAll().iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        //when
        recipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(recipeCommand.getId(), savedRecipeCommand.getId());
        assertEquals(recipeCommand.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(recipeCommand.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}