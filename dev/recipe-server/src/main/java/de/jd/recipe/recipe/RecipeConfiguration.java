package de.jd.recipe.recipe;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.jd.recipe.IdConverter;
import de.jd.recipe.MongoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MongoConfiguration.class)
public class RecipeConfiguration {

    @Bean(name = "recipe-collection")
    public DBCollection openRecipeCollection(MongoClient mongoClient) {
        DB myDB = mongoClient.getDB("recipe-server");
        return myDB.getCollection("recipe");
    }

    @Bean(name = "recipeIdConverter")
    public IdConverter recipeIdConverter() {
        IdConverter idConverter = new IdConverter();
        idConverter.setIdPrefix("recipe-");
        return idConverter;
    }

    @Bean(name = "recipeDao")
    public RecipeDao recipeDao(@Qualifier("recipe-collection") DBCollection recipeCollection,
                               @Qualifier("recipeIdConverter") IdConverter idConverter) {
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.setRecipeCollection(recipeCollection);
        recipeDao.setIdConverter(idConverter);
        return recipeDao;
    }
}
