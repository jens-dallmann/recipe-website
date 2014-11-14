package de.jd.recipeEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RecipeController {
    Logger LOG = LoggerFactory.getLogger(RecipeController.class);
    @RequestMapping("/recipe")
    public String getRecipe(Model m) {
        LOG.error("SEVERE IN GET RECIPE");
        LOG.warn("WARNING IN GET RECIPE");
        m.addAttribute("title", "abcd");
        return "recipe";
    }
}