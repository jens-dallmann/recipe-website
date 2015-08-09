package de.jd.server.demodata.communicator;

import de.jd.entities.Category;
import de.jd.entities.CategoryImpl;
import de.jd.entities.Recipe;
import de.jd.entities.RecipeImpl;
import de.jd.server.demodata.handler.HandlerResult;
import de.jd.urls.CategoryServerUrls;
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
    private CategoryServerUrls categoryServerUrls;

    @Override
    public HandlerResult postRecipe(Recipe recipe) {
        String postUrl = recipeServerUrls.postRecipeUrl();
        HttpEntity request = getHttpEntity(recipe);
        ResponseEntity<RecipeImpl> recipeResponseEntity = restTemplate.postForEntity(postUrl, request, RecipeImpl.class);
        return buildResult(recipeResponseEntity);
    }

    private HandlerResult buildResult(ResponseEntity<?> recipeResponseEntity) {
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

    private HttpEntity getHttpEntity(Object body) {
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return new HttpEntity(body, headers);
    }

    @Override
    public HandlerResult postCategory(Category category) {
        String postUrl = categoryServerUrls.postCategory();

        HttpEntity httpEntity = getHttpEntity(category);
        ResponseEntity<CategoryImpl> categoryResponseEntity = restTemplate.postForEntity(postUrl, httpEntity, CategoryImpl.class);
        return buildResult(categoryResponseEntity);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }

    public void setCategoryServerUrls(CategoryServerUrls categoryServerUrls) {
        this.categoryServerUrls = categoryServerUrls;
    }
}
