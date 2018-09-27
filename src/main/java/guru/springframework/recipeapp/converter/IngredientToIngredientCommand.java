package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.IngredientCommand;
import guru.springframework.recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomComverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomComverter) {
        this.uomComverter = uomComverter;
    }


    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUom(uomComverter.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
