package de.jd.recipeWebsite;

import de.jd.entities.Recipe;
import de.jd.entities.RecipeImpl;
import de.jd.status.OneRecipeStatusResponse;
import de.jd.urls.RecipeServerUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class RecipeController {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    public RestTemplate restTemplate;

    public RecipeServerUrls recipeServerUrls;

    @RequestMapping("/recipe/{id}")
    public String getRecipe(Model m, @PathVariable String id, HttpServletRequest request) {
        String recipeUrl = recipeServerUrls.getRecipeUrl(id);
        LOG.debug("Retrieve Recipe with id \"{}\" by using url: \"{}\"", id, recipeUrl);
        OneRecipeStatusResponse statusResponse = restTemplate.getForObject(recipeUrl, OneRecipeStatusResponse.class);
        if (statusResponse.getStatus().isSuccessfull()) {
            m.addAttribute("title", statusResponse.getRecipe().getTitle());
            m.addAttribute("images", statusResponse.getRecipe().getImages());
            return "recipe";
        } else {
            m.addAttribute("errorMessage", statusResponse.getStatus().getErrorMsg());
            return "error";
        }
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

    @RequestMapping("/sidebar")
    public ModelAndView getSidebar() {
        String allRecipesUrl = recipeServerUrls.getAllRecipesUrl();

        LOG.debug("Retrieve all recipes by using url: \"{}\"", allRecipesUrl);
        List<Recipe> recipe = restTemplate.getForObject(allRecipesUrl, List.class);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("recipes", recipe);
        modelAndView.setViewName("sidebar");
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/add", method = RequestMethod.GET)
    public ModelAndView addRecipeForm() {
        ModelAndView modelAndView = new ModelAndView("addRecipe", "recipe-entity", new RecipeImpl());
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
    public ModelAndView addRecipe(@RequestBody Recipe recipe) {
        String postRecipeUrl = recipeServerUrls.postRecipeUrl();
        String allRecipesUrl = recipeServerUrls.getAllRecipesUrl();

        LOG.debug("Posting Recipe (\"id\":{},\"title\":{}) to url: \"{}\"", recipe.getId(), recipe.getTitle(), postRecipeUrl);
        restTemplate.postForEntity(postRecipeUrl, recipe, RecipeImpl.class);
        LOG.debug("Retrieve all recipes by using url: \"{}\"", allRecipesUrl);
        List all = restTemplate.getForObject(allRecipesUrl, List.class);

        ModelAndView modelAndView = new ModelAndView("listRecipes");
        modelAndView.addObject("recipes", all);
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/header", method = RequestMethod.GET)
    public ModelAndView header() {
        LOG.debug("Header requested");
        ModelAndView modelAndView = new ModelAndView("header");
        return modelAndView;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}