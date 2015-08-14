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
    public IndexController indexController(@Qualifier(value="contextService") ContextService contextService,
                                           @Qualifier(value="recipeServerUrls") RecipeServerUrls recipeServerUrls,
                                           @Qualifier(value="restTemplate") RestTemplate restTemplate,
                                           List<MainHandler> mainHandlerList) {
        IndexController indexController = new IndexController();
        indexController.setMainHandlers(mainHandlerList);
        indexController.setContextService(contextService);
        indexController.setRecipeServerUrls(recipeServerUrls);
        indexController.setRestTemplate(restTemplate);
        return indexController;
    }


    @Bean
    public CategoryMainHandler categoryMainHandler(@Qualifier("contextService") ContextService contextService) {
        CategoryMainHandler categoryMainHandler = new CategoryMainHandler();
        categoryMainHandler.setContextService(contextService);
        return categoryMainHandler;
    }

    @Bean
    public RecipeMainHandler recipeMainHandler(@Qualifier("restTemplate") RestTemplate restTemplate,
                                               @Qualifier("recipeServerUrls") RecipeServerUrls recipeServerUrls,
                                               @Qualifier("contextService") ContextService contextService) {
        RecipeMainHandler recipeMainHandler = new RecipeMainHandler();
        recipeMainHandler.setRecipeServerUrls(recipeServerUrls);
        recipeMainHandler.setRestTemplate(restTemplate);
        recipeMainHandler.setContextService(contextService);
        return recipeMainHandler;
    }

    @Bean (name = "contextService")
    public ContextService contextService(@Qualifier("restTemplate") RestTemplate restTemplate,
                                         @Qualifier("categoryServerUrls") CategoryServerUrls categoryServerUrls) {
        ContextServiceImpl contextService = new ContextServiceImpl();
        contextService.setCategoryServerUrls(categoryServerUrls);
        contextService.setRestTemplate(restTemplate);
        return contextService;
    }
}
