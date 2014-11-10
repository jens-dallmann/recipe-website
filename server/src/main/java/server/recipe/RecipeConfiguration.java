package server.recipe;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class RecipeConfiguration {
    @Bean
    public RecipeService recipeService() {
        return new RecipeService();
    }
}
