package de.jd.recipe;

import de.jd.recipe.category.CategoryConfiguration;
import de.jd.recipe.category.CategoryDao;
import de.jd.recipe.category.CategoryService;
import de.jd.recipe.recipe.RecipeConfiguration;
import de.jd.recipe.recipe.RecipeDao;
import de.jd.recipe.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RecipeConfiguration.class, CategoryConfiguration.class})
public class RequestHandlerConfiguration {
    @Bean(name = "recipeService")
    public RecipeService recipeController(@Qualifier("recipeDao") RecipeDao recipeDao) {
        RecipeService recipeService = new RecipeService();
        recipeService.setRecipeDao(recipeDao);
        return recipeService;
    }

    @Bean(name = "categoryService")
    public CategoryService categoryService(@Qualifier("categoryDao") CategoryDao categoryDao) {
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryDao(categoryDao);
        return categoryService;
    }
}
