package de.jd.recipeWebsite;

import de.jd.status.OneRecipeStatusResponse;
import de.jd.urls.RecipeServerUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
    private List<MainHandler> mainHandlers;
    private ContextService contextService;
    private RecipeServerUrls recipeServerUrls;
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("isIncluded", false);
        return modelAndView;
    }

    @RequestMapping("/main/{categoryId}")
    public ModelAndView includeCategory(@PathVariable("categoryId") String categoryId) {
        LOG.debug("Index with category context requested");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Context context = contextService.resolveContext("categoryMain", categoryId, true);
        modelAndView.addObject("context", context);
        return modelAndView;
    }

    @RequestMapping("/main/{categoryId}/recipe/{recipeId}")
    public ModelAndView includeRecipe(@PathVariable("recipeId") String recipeId,
                                      @PathVariable("categoryId") String categoryId) {
        LOG.debug("Index with recipe requested. Category used for context: {}, Recipe Id: {}", categoryId, recipeId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String recipeUrl = recipeServerUrls.getRecipeUrl(recipeId);
        OneRecipeStatusResponse response = restTemplate.getForObject(recipeUrl, OneRecipeStatusResponse.class);
        Context context = contextService.resolveContext("recipeMain", categoryId, response.getRecipe(), true);
        modelAndView.addObject("context", context);
        return modelAndView;
    }

    @RequestMapping("/main/include/{context}/{categoryId}")
    public ModelAndView includeMain(@PathVariable("context") String context,
                                    @PathVariable("categoryId") String categoryId,
                                    @RequestParam(value = "entityId", required = false) String entityId) {
        LOG.info("Include in Main-Placement. Search Handler for context: {}", context);
        for (MainHandler mainHandler : mainHandlers) {
            if (mainHandler.canHandle(context, entityId)) {
                ModelAndView handle = mainHandler.handle(categoryId, entityId);
                return handle;
            }
        }
        LOG.warn("Can't find handler to include context in Main-Placement. Properties: contextName={}, categoryId={}, entityId={}", context, categoryId, entityId);
        return new ModelAndView("index");
    }

    public void setMainHandlers(List<MainHandler> mainHandlers) {
        this.mainHandlers = mainHandlers;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    public void setRecipeServerUrls(RecipeServerUrls recipeServerUrls) {
        this.recipeServerUrls = recipeServerUrls;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
