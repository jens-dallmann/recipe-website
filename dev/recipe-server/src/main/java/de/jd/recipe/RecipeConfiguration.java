package de.jd.recipe;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class RecipeConfiguration {

    private final static Logger log = LoggerFactory.getLogger(RecipeConfiguration.class);

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            log.error("Unknown Host Exception during creating MongoClient");
        }
        return mongoClient;
    }

    @Bean(name="recipe-collection")
    public DBCollection openRecipeCollection(MongoClient mongoClient) {
        DB myDB = mongoClient.getDB("recipe-server");
        return myDB.getCollection("recipe");
    }

    @Bean
    public RecipeDao recipeDao(MongoClient mongoClient) {
        DBCollection recipeCollection = openRecipeCollection(mongoClient);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.setRecipeCollection(recipeCollection);
        return recipeDao;
    }

    @Bean
    public RecipeService recipeController(RecipeDao recipeDao) {
        RecipeService recipeService = new RecipeService();
        recipeService.setRecipeDao(recipeDao);
        return recipeService;
    }
}
