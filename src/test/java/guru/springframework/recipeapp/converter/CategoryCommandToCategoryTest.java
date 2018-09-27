package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.CategoryCommand;
import guru.springframework.recipeapp.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    private CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() throws Exception {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void testConvert_NullObject() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testConvert_EmptyObject() {
        assertNotNull("Category Command shouldn't be null", categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    public void testConvert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = categoryCommandToCategory.convert(categoryCommand);

        //then
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}