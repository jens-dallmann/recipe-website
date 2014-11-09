package server.recipe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeConfiguration {
    @Bean
    public RecipeService recipeService() {
        return new RecipeService();
    }
}
