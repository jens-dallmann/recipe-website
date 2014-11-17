package de.jd.recipeEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RecipeServerUrls {

    private static final Logger log = LoggerFactory.getLogger(RecipeServerUrls.class);

    private String recipeServerBaseUrl;

    @Autowired
    private Environment environment;

    public String getAllRecipesUrl() {
        return getRecipeServerBaseUrl() + "/recipe/";
    }

    public String getRecipeUrl(int id) {
        String template = getRecipeServerBaseUrl() + "/recipe/%s";
        String expandedUrl = String.format(template, id);

        return expandedUrl;
    }

    public String postRecipeUrl() {
        String url = getRecipeServerBaseUrl() + "/recipe/post";
        return url;
    }

    public String getRecipeServerBaseUrl() {
        return environment.getProperty("recipe.service.base.url");
    }

    public void setRecipeServerBaseUrl(String recipeServerBaseUrl) {
        log.info("Set recipe server base url: \"{}\"", recipeServerBaseUrl);
        this.recipeServerBaseUrl = recipeServerBaseUrl;
    }
}
