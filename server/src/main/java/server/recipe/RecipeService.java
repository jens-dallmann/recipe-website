package server.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeService {
    @RequestMapping("/recipe")
    public Recipe recipe() {
        Recipe recipe = new Recipe();
        recipe.setTitle("anyTitle");
        recipe.setId(0);
        return recipe;
    }
}
