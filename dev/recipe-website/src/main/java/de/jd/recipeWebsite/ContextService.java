package de.jd.recipeWebsite;

public interface ContextService {

    /**
     * Create a context. A context gives the main page the hint how it must be rendered.
     *
     * @param contextName the contextname to resolve the mainhandler on main-placement include request
     * @param categoryId the id used as category.
     * @param entity the entity which will be rendered in the placement.
     * @param isIncluded if included the main page will render an inner placement with the given context
     * @return A context with the given properties set.
     */
    Context resolveContext(String contextName, String categoryId, Object entity, boolean isIncluded);

    /**
     * Create a context. A context gives the main page the hint how it must be rendered.
     * Given categoryId will be used as category and entity.
     *
     * @param contextName the contextname to resolve the mainhandler on main-placement include request
     * @param categoryId the id used as category.
     * @param isIncluded if included the main page will render an inner placement with the given context
     * @return A context with the given properties set.
     */
    Context resolveContext(String contextName, String categoryId, boolean isIncluded);
}
