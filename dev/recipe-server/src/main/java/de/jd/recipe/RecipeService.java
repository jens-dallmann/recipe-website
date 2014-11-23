package de.jd.recipe;

import de.jd.entities.RecipeImpl;
import de.jd.status.OneRecipeStatusResponse;
import de.jd.status.OneRecipeStatusResponseBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(RecipeService.class);
    public static final String RECIPE_NOT_FOUND_IN_DATABASE = "Recipe not found in database";
    public static final String RECIPE_NOT_FOUND = "RECIPE_NOT_FOUND";

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value = "/recipe/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public OneRecipeStatusResponse recipe(@PathVariable String id) {
        RecipeImpl queryObject = new RecipeImpl();
        queryObject.setId(id);

        RecipeImpl recipe = recipeDao.findOne(queryObject);
        OneRecipeStatusResponseBuilder builder = new OneRecipeStatusResponseBuilder();
        if(recipe == null) {
            OneRecipeStatusResponse response = builder.withError(RECIPE_NOT_FOUND, RECIPE_NOT_FOUND_IN_DATABASE).build();
            return response;
        }

        OneRecipeStatusResponse response = builder.withRecipe(recipe).build();
        return response;
    }

    @RequestMapping(value = "/recipe", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<RecipeImpl> getAll() {
        return recipeDao.findAll();
    }

    @RequestMapping(value = "/recipe/post", consumes="application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public void post(@RequestBody RecipeImpl recipe) {
        recipeDao.post(recipe);
    }
}
