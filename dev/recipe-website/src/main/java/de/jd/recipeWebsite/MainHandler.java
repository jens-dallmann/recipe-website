package de.jd.recipeWebsite;

import org.springframework.web.servlet.ModelAndView;

/**
 * The main handler handles includes in the main placement referenced in the index.jsp
 */
public interface MainHandler {

    /**
     * Defines if the handler can handle a request.
     *
     * @param context the contextName this handler is asked to handle
     * @param entityId the entityId so the handler can decide if he wants to handle this entity
     * @return true if the handler can handle this request
     */
    boolean canHandle(String context, String entityId);

    /**
     * Handle the request with the category and the entityId
     *
     * @param categoryId category
     * @param entityId id of the entity to render
     *
     * @return full build model and view containing the context
     */
    ModelAndView handle(String categoryId, String entityId);
}
