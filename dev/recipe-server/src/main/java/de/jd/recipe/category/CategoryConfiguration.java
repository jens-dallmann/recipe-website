package de.jd.recipe.category;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.jd.recipe.IdConverter;
import de.jd.recipe.MongoConfiguration;
import de.jd.recipe.recipe.RecipeConfiguration;
import de.jd.recipe.recipe.RecipeDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoConfiguration.class, RecipeConfiguration.class})
public class CategoryConfiguration {

    @Bean(name="category-collection")
    public DBCollection openCategoryCollection(MongoClient mongoClient) {
        DB myDB = mongoClient.getDB("recipe-server");
        return myDB.getCollection("category");
    }

    @Bean(name = "categoryDao")
    public CategoryDao categoryDao(@Qualifier("category-collection") DBCollection recipeCollection,
                                   @Qualifier("categoryIdConverter") IdConverter idConverter,
                                   @Qualifier("recipeDao") RecipeDao recipeDao) {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.setCollection(recipeCollection);
        categoryDao.setIdConverter(idConverter);
        categoryDao.setRecipeDao(recipeDao);
        return categoryDao;
    }
    @Bean(name = "categoryIdConverter")
    public IdConverter categoryIdConverter() {
        IdConverter idConverter = new IdConverter();
        idConverter.setIdPrefix("category-");
        return idConverter;
    }
}
