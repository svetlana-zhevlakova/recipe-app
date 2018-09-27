package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.CategoryCommand;
import guru.springframework.recipeapp.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    private CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testConvert_NullObject() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testConvert_EmptyObject() {
        assertNotNull("Category shouldn't be null", categoryToCategoryCommand.convert(new Category()));
    }


    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

        //then
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}