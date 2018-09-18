package guru.springframework.recipeapp.controller;

import guru.springframework.recipeapp.model.Recipe;
import guru.springframework.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getAllRecipes(Model model) {
        log.debug("I'm starting the controller...");
        List<Recipe> all = recipeService.getRecipes();
        model.addAttribute("recipes", all);
        log.debug("I've ended the controller.");
        return "index";
    }
}
