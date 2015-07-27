package de.jd.recipe;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import de.jd.entities.RecipeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeDao {
    private DBCollection recipeCollection;

    private final static Logger log = LoggerFactory.getLogger(RecipeDao.class);

    @Resource(name = "recipe-collection")
    public void setRecipeCollection(DBCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    public RecipeImpl findOne(RecipeImpl queryRecipe) {
        log.info("Query for recipe with id: " + queryRecipe.getId());
        BasicDBObject queryObject = new BasicDBObject(queryRecipe.getProperties());
        DBObject storedRecipe = recipeCollection.findOne(queryObject);
        if (storedRecipe != null) {
            Map<String, Object> map = (Map<String, Object>) storedRecipe.toMap();
            return new RecipeImpl(map);
        }
        return null;
    }

    public List<RecipeImpl> findAll() {
        log.info("Find all Recipes");
        DBCursor storedRecipes = recipeCollection.find();
        List<RecipeImpl> allRecipes = new ArrayList<RecipeImpl>();

        for (DBObject oneRecipe : storedRecipes) {
            allRecipes.add(new RecipeImpl(oneRecipe.toMap()));
        }
        log.info("Found {} recipes", allRecipes.size());
        return allRecipes;
    }

    public int retrieveHighestId() {
        List<RecipeImpl> all = findAll();
        int max = 0;
        for (RecipeImpl recipe : all) {
            String id = recipe.getId();
            int internalId = RecipeIdResolver.toInternalId(id);
            max = Math.max(max, internalId);
        }
        return max;
    }

    public synchronized void post(RecipeImpl recipe) {
        recipe.setId(RecipeIdResolver.toExternalId(retrieveHighestId() + 1));
        Map<String, Object> properties = recipe.getProperties();
        BasicDBObject basicDBObject = new BasicDBObject(properties);
        recipeCollection.insert(basicDBObject);
    }
}
