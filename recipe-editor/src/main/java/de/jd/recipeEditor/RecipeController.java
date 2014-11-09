package de.jd.recipeEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

	@RequestMapping("/recipe")
	public String getRecipe(Model m) {
		m.addAttribute("title", "abcd");
		return "recipe";
	}
}
