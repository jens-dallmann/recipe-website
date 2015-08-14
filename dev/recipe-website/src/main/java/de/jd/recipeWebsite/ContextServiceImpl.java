package de.jd.recipeWebsite;

import de.jd.entities.CategoryImpl;
import de.jd.urls.CategoryServerUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ContextServiceImpl implements ContextService {
    private static final Logger LOG = LoggerFactory.getLogger(ContextServiceImpl.class);

    private RestTemplate restTemplate;

    private CategoryServerUrls categoryServerUrls;

    @Override
    public Context resolveContext(String contextName, String categoryId, Object entity, boolean isIncluded) {
        LOG.debug("Resolve context with contextName: {}, categoryId: {}, entity: {}, included: {}", contextName, categoryId, entity, isIncluded);
        Context context = new Context();
        CategoryImpl forEntity = restTemplate.getForObject(categoryServerUrls.getCategory(categoryId), CategoryImpl.class);
        context.setEntity(entity);
        context.setCategory(forEntity);
        context.setContextName(contextName);
        context.setIncluded(isIncluded);
        return context;
    }

    @Override
    public Context resolveContext(String contextName, String categoryId, boolean isIncluded) {
        LOG.debug("Resolve context for category: "+categoryId);
        CategoryImpl category = restTemplate.getForObject(categoryServerUrls.getCategory(categoryId), CategoryImpl.class);
        return resolveContext(contextName, categoryId, category, isIncluded);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setCategoryServerUrls(CategoryServerUrls categoryServerUrls) {
        this.categoryServerUrls = categoryServerUrls;
    }
}
