package de.jd.recipe.recipe;

import de.jd.entities.RecipeImpl;
import de.jd.status.OneRecipeStatusResponse;
import de.jd.status.OneRecipeStatusResponseBuilder;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipeService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(RecipeService.class);
    public static final String RECIPE_NOT_FOUND_IN_DATABASE = "Recipe not found in database";
    public static final String RECIPE_NOT_FOUND = "RECIPE_NOT_FOUND";

    private RecipeDao recipeDao;

    @RequestMapping(value = "/recipe/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public OneRecipeStatusResponse recipe(@PathVariable("id") String id) {
        LOG.info("Try to get recipe");
        RecipeImpl queryObject = new RecipeImpl();
        queryObject.setId(id);
        RecipeImpl recipe = recipeDao.findOne(queryObject);

        OneRecipeStatusResponseBuilder builder = new OneRecipeStatusResponseBuilder();
        if(recipe == null) {
            return builder.withError(RECIPE_NOT_FOUND, RECIPE_NOT_FOUND_IN_DATABASE).build();
        }

        return builder.withRecipe(recipe).build();
    }

    @RequestMapping(value = "/recipe", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<RecipeImpl> getAll() {
        LOG.info("Try to get all recipes");

        return recipeDao.findAll();
    }

    @RequestMapping(value = "/recipe/post", consumes="application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public void post(@RequestBody RecipeImpl recipe) {
        LOG.info("Try to post recipe");

        recipeDao.post(recipe);
    }

    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }
}
