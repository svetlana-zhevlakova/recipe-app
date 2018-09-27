package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.CategoryCommand;
import guru.springframework.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }
}
