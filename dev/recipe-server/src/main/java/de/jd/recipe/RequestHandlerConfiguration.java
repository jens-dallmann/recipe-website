package de.jd.recipe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(RecipeConfiguration.class)
public class RequestHandlerConfiguration {
    @Bean
    public RecipeService recipeController(RecipeDao recipeDao) {
        RecipeService recipeService = new RecipeService();
        recipeService.setRecipeDao(recipeDao);
        return recipeService;
    }
}
