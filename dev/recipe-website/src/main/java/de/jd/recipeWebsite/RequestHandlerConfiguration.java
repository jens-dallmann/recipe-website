package de.jd.recipeWebsite;

import de.jd.urls.RecipeServerUrls;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RecipeWebsiteConfiguration.class)
public class RequestHandlerConfiguration {

    @Bean (name = "recipeController")
    public RecipeController recipeController(@Qualifier(value="restTemplate") RestTemplate restTemplate,
                                             @Qualifier(value="recipeServerUrls") RecipeServerUrls recipeServerUrls) {
        RecipeController recipeController = new RecipeController();
        recipeController.setRecipeServerUrls(recipeServerUrls);
        recipeController.setRestTemplate(restTemplate);
        return recipeController;
    }

    @Bean (name = "indexController")
    public IndexController indexController() {
        return new IndexController();
    }
}
