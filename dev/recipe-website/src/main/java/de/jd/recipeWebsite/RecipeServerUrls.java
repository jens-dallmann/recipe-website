package de.jd.recipeWebsite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RecipeServerUrls {

    private static final Logger log = LoggerFactory.getLogger(RecipeServerUrls.class);

    private String recipeServerBaseUrl;

    public String getAllRecipesUrl() {
        return getRecipeServerBaseUrl() + "/recipe/";
    }

    public String getRecipeUrl(String id) {
        String template = getRecipeServerBaseUrl() + "/recipe/%s";
        return String.format(template, id);
    }

    public String postRecipeUrl() {
        return getRecipeServerBaseUrl() + "/recipe/post";
    }

    public String getRecipeServerBaseUrl() {
        return recipeServerBaseUrl;
    }

    public void setRecipeServerBaseUrl(String recipeServerBaseUrl) {
        log.info("Set recipe server base url: \"{}\"", recipeServerBaseUrl);
        this.recipeServerBaseUrl = recipeServerBaseUrl;
    }
}
