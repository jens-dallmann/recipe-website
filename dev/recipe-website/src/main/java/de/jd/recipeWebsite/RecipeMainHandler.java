package de.jd.recipeWebsite;

import de.jd.status.OneRecipeStatusResponse;
import de.jd.urls.RecipeServerUrls;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class RecipeMainHandler implements MainHandler {

    private RecipeServerUrls recipeServerUrls;
    private RestTemplate restTemplate;

    @Override
    public boolean canHandle(String context, String urlParam) {
        return "recipeMain".equals(context);
    }

    @Override
    public ModelAndView handle(String urlParam) {
        String recipeUrl = recipeServerUrls.getRecipeUrl(urlParam);
        OneRecipeStatusResponse oneRecipeStatusResponse = restTemplate.getForObject(recipeUrl, OneRecipeStatusResponse.class);
        ModelAndView recipeMaV = new ModelAndView("recipe");
        recipeMaV.addObject("recipe", oneRecipeStatusResponse.getRecipe());
        return recipeMaV;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
