package de.jd.recipe;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value = "/recipe/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Recipe recipe(@PathVariable int id) {
        Recipe queryObject = new Recipe();
        queryObject.setId(id);

        Recipe recipe = recipeDao.findOne(queryObject);
        return recipe;
    }

    @RequestMapping(value = "/recipe", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Recipe> getAll() {
        return recipeDao.findAll();
    }

    @RequestMapping(value = "/recipe/post", consumes="application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public void post(@RequestBody Recipe recipe) {
        recipeDao.post(recipe);
    }
}
