package de.jd.recipeWebsite;

import de.jd.status.OneRecipeStatusResponse;
import de.jd.urls.RecipeServerUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class RecipeMainHandler implements MainHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeMainHandler.class);

    private RecipeServerUrls recipeServerUrls;
    private RestTemplate restTemplate;
    private ContextService contextService;


    @Override
    public boolean canHandle(String context, String entityId) {
        return "recipeMain".equals(context) && !StringUtils.isEmpty(entityId);
    }

    @Override
    public ModelAndView handle(String categoryId, String entityId) {
        LOG.debug("Handle Recipe Request with arguments: categoryId={}, entityId={}", categoryId, entityId);
        String recipeUrl = recipeServerUrls.getRecipeUrl(entityId);
        OneRecipeStatusResponse oneRecipeStatusResponse = restTemplate.getForObject(recipeUrl, OneRecipeStatusResponse.class);
        Context context = contextService.resolveContext("recipeMain", categoryId, oneRecipeStatusResponse.getRecipe(), true);
        ModelAndView recipeMaV = new ModelAndView("recipe");
        recipeMaV.addObject("context", context);
        return recipeMaV;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }
}
