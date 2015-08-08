package de.jd.server.demodata.communicator;

import de.jd.entities.Recipe;
import de.jd.entities.RecipeImpl;
import de.jd.server.demodata.handler.HandlerResult;
import de.jd.urls.RecipeServerUrls;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RecipeServerCommunicatorImpl implements RecipeServerCommunicator {
    private RestTemplate restTemplate;
    private RecipeServerUrls recipeServerUrls;

    @Override
    public HandlerResult postRecipe(Recipe recipe) {
        String postUrl = recipeServerUrls.postRecipeUrl();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity request = new HttpEntity(recipe, headers);
        ResponseEntity<RecipeImpl> recipeResponseEntity = restTemplate.postForEntity(postUrl, request, RecipeImpl.class);
        HttpStatus statusCode = recipeResponseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            HandlerResult handlerResult = new HandlerResult();
            handlerResult.setSuccessfull(true);
            return handlerResult;
        }
        HandlerResult result = new HandlerResult();
        result.setSuccessfull(false);
        return result;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }
}
