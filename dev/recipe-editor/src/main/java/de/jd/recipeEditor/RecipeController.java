package de.jd.recipeEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class RecipeController {
    Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    @RequestMapping("/recipe")
    public String getRecipe(Model m) {
        RestTemplate restTemplate = new RestTemplate();
        Recipe recipe = restTemplate.getForObject("http://localhost:8090/recipe-server/recipe", Recipe.class);
        m.addAttribute("title", recipe.getTitle());
        return "recipe";
    }
}