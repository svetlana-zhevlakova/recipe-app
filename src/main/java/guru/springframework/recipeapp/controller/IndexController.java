package guru.springframework.recipeapp.controller;

import guru.springframework.recipeapp.model.Recipe;
import guru.springframework.recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getAllRecipes(Model model) {
        List<Recipe> all = recipeService.getRecipes();
        model.addAttribute("recipes", all);
        return "index";
    }
}
