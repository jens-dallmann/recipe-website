package de.jd.recipe.category;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import de.jd.entities.Category;
import de.jd.entities.CategoryImpl;
import de.jd.entities.Recipe;
import de.jd.entities.RecipeImpl;
import de.jd.recipe.IdConverter;
import de.jd.recipe.recipe.RecipeDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDao {
    private DBCollection collection;
    private IdConverter idConverter;
    private RecipeDao recipeDao;

    public Category findCategory(CategoryImpl queryCategory) {
        BasicDBObject queryObject = new BasicDBObject(queryCategory.getProperties());
        DBObject storedCategory = collection.findOne(queryObject);
        if (storedCategory != null) {
            Map<String, Object> map = withRecipeObjects(storedCategory);
            return new CategoryImpl(map);
        }
        return null;
    }

    private Map<String, Object> withRecipeObjects(DBObject storedCategory) {
        Map<String, Object> map = new HashMap<>((Map<String, Object>) storedCategory.toMap());
        Object recipes = map.get("recipes");
        if(recipes instanceof List) {
            List<Recipe> asRecipes = toRecipes((List<String>) recipes);
            map.put("recipes", asRecipes);

        }
        return map;
    }

    private List<Recipe> toRecipes(List<String> recipeIds) {
        List<Recipe> recipes = new ArrayList<>();
        for(String recipeId: recipeIds) {
            RecipeImpl queryRecipe = new RecipeImpl();
            queryRecipe.setId(recipeId);
            Recipe recipe = recipeDao.findOne(queryRecipe);
            recipes.add(recipe);
        }
        return recipes;
    }

    public synchronized void post(CategoryImpl category) {
        category.setId(idConverter.toExternalId(retrieveHighestId() + 1));
        List<Recipe> recipes = category.getRecipes();
        Map<String, Object> properties = new HashMap<>(category.getProperties());
        List<String> recipeIds = new ArrayList<>();
        for(Recipe recipe: recipes) {
            recipeIds.add(recipe.getId());
        }
        properties.put("recipes", recipeIds);

        BasicDBObject basicDBObject = new BasicDBObject(properties);
        collection.insert(basicDBObject);
    }

    public List<CategoryImpl> findAll() {
        DBCursor storedCategories = collection.find();
        List<CategoryImpl> allCategories = new ArrayList<>();

        for (DBObject oneCategory : storedCategories) {
            Map<String, Object> complete = withRecipeObjects(oneCategory);
            allCategories.add(new CategoryImpl(complete));
        }
        return allCategories;
    }

    public int retrieveHighestId() {
        List<CategoryImpl> all = findAll();
        int max = 0;
        for (CategoryImpl category : all) {
            String id = category.getId();
            int internalId = idConverter.toInternalId(id);
            max = Math.max(max, internalId);
        }
        return max;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }

    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public void setCollection(DBCollection collection) {
        this.collection = collection;
    }
}
