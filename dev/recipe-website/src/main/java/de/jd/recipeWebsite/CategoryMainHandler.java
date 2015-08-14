package de.jd.recipeWebsite;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

public class CategoryMainHandler implements MainHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryMainHandler.class);
    private ContextService contextService;

    @Override
    public boolean canHandle(String context, String entityId) {
        return context.equals("categoryMain");
    }

    @Override
    public ModelAndView handle(String categoryId, String entityId) {
        LOG.debug("Handle Category Request with properties: categoryId={}, entityId={}", categoryId, entityId);
        ModelAndView includeCategory = new ModelAndView("includeCategory");
        Context context = contextService.resolveContext("categoryMain", categoryId, true);
        includeCategory.addObject("context", context);
        return includeCategory;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }
}