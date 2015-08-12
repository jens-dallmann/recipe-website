package de.jd.recipeWebsite;

import de.jd.urls.CategoryServerUrls;
import de.jd.urls.RecipeServerUrls;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Import(RecipeWebsiteConfiguration.class)
public class RequestHandlerConfiguration {

    @Bean (name = "recipeController")
    public RecipeController recipeController(@Qualifier(value="restTemplate") RestTemplate restTemplate,
                                             @Qualifier(value="recipeServerUrls") RecipeServerUrls recipeServerUrls,
                                             @Qualifier(value="categoryServerUrls")CategoryServerUrls categoryServerUrls) {
        RecipeController recipeController = new RecipeController();
        recipeController.setRecipeServerUrls(recipeServerUrls);
        recipeController.setCategoryServiceUrls(categoryServerUrls);
        recipeController.setRestTemplate(restTemplate);
        return recipeController;
    }

    @Bean (name = "indexController")
    public IndexController indexController(@Qualifier(value="restTemplate") RestTemplate restTemplate,
                                           @Qualifier(value="categoryServerUrls") CategoryServerUrls categoryServerUrls,
                                           List<MainHandler> mainHandlerList) {
        IndexController indexController = new IndexController();
        indexController.setMainHandlers(mainHandlerList);
        return indexController;
    }


    @Bean
    public CategoryMainHandler categoryMainHandler(@Qualifier("restTemplate") RestTemplate restTemplate,
                                                   @Qualifier("categoryServerUrls") CategoryServerUrls categoryServerUrls) {
        CategoryMainHandler categoryMainHandler = new CategoryMainHandler();
        categoryMainHandler.setCategoryServerUrls(categoryServerUrls);
        categoryMainHandler.setRestTemplate(restTemplate);
        return categoryMainHandler;
    }

    @Bean
    public RecipeMainHandler recipeMainHandler(@Qualifier("restTemplate") RestTemplate restTemplate,
                                                   @Qualifier("recipeServerUrls") RecipeServerUrls recipeServerUrls) {
        RecipeMainHandler recipeMainHandler = new RecipeMainHandler();
        recipeMainHandler.setRecipeServerUrls(recipeServerUrls);
        recipeMainHandler.setRestTemplate(restTemplate);
        return recipeMainHandler;
    }
}
