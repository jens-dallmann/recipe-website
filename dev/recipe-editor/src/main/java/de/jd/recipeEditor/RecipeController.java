package de.jd.recipeEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class RecipeController {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public RecipeServerUrls recipeServerUrls;

    @RequestMapping("/recipe/{id}")
    public String getRecipe(Model m, @PathVariable int id) {
        String recipeUrl = recipeServerUrls.getRecipeUrl(id);

        LOG.debug("Retrieve Recipe with id \"{}\" by using url: \"{}\"", id, recipeUrl);
        Recipe recipe = restTemplate.getForObject(recipeUrl, Recipe.class);

        m.addAttribute("title", recipe.getTitle());
        return "recipe";
    }

    @RequestMapping("/recipe")
    public ModelAndView getAllRecipes() {
        String allRecipesUrl = recipeServerUrls.getAllRecipesUrl();

        LOG.debug("Retrieve all recipes by using url: \"{}\"", allRecipesUrl);
        List<Recipe> recipe = restTemplate.getForObject(allRecipesUrl, List.class);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("recipes", recipe);
        modelAndView.setViewName("listRecipes");
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/add", method = RequestMethod.GET)
    public ModelAndView addRecipeForm() {
        ModelAndView modelAndView = new ModelAndView("addRecipe", "recipe-entity", new Recipe());
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
    public ModelAndView addRecipe(@RequestBody Recipe recipe) {
        String postRecipeUrl = recipeServerUrls.postRecipeUrl();
        String allRecipesUrl = recipeServerUrls.getAllRecipesUrl();

        LOG.debug("Posting Recipe (\"id\":{},\"title\":{}) to url: \"{}\"", recipe.getId(), recipe.getTitle(), postRecipeUrl);
        restTemplate.postForEntity(postRecipeUrl, recipe, Recipe.class);
        LOG.debug("Retrieve all recipes by using url: \"{}\"", allRecipesUrl);
        List all = restTemplate.getForObject(allRecipesUrl, List.class);

        ModelAndView modelAndView = new ModelAndView("listRecipes");
        modelAndView.addObject("recipes", all);
        return modelAndView;
    }
}