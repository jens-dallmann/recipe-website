package de.jd.recipe;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecipeDao {

    @Autowired
    private MongoClient mongoClient;

    private final static Logger log = LoggerFactory.getLogger(RecipeDao.class);

    public void put(Recipe recipe) {
        DBCollection recipes = openRecipeCollection();

        BasicDBObject dbObject = new BasicDBObject(recipe.getProperties());
        recipes.insert(dbObject);
    }

    public RecipeDao() {
        super();
    }

    public Recipe findOne(Recipe queryRecipe) {
        DBCollection recipeCollection = openRecipeCollection();
        log.info("Query for recipe with id: "+queryRecipe.getId());
        BasicDBObject queryObject = new BasicDBObject(queryRecipe.getProperties());
        DBObject storedRecipe = recipeCollection.findOne(queryObject);
        Map<String, Object> map = (Map<String, Object>) storedRecipe.toMap();
        return new Recipe(map);
    }

    public List<Recipe> findAll() {
        DBCollection recipeCollection = openRecipeCollection();
        log.info("Find all Recipes");
        DBCursor storedRecipes = recipeCollection.find();
        List<Recipe> allRecipes = new ArrayList<Recipe>();

        for(DBObject oneRecipe: storedRecipes) {
            allRecipes.add(new Recipe(oneRecipe.toMap()));
        }
        return allRecipes;
    }

    private DBCollection openRecipeCollection() {
        DB myDB = mongoClient.getDB("myDB");
        return myDB.getCollection("recipe");
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void post(Recipe recipe) {
        openRecipeCollection().insert(new BasicDBObject(recipe.getProperties()));
    }
}
