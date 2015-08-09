package de.jd.server.demodata.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jd.entities.Category;
import de.jd.entities.Recipe;
import de.jd.server.demodata.communicator.RecipeServerCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class CategoryDemodataHandler implements DemodataHandler {
    private final static Logger logger = LoggerFactory.getLogger(CategoryDemodataHandler.class);
    private ObjectMapper objectMapper;
    private RecipeServerCommunicator recipeServerCommunicator;

    @Override
    public HandlerResult handle(Resource resource) {
        try {
            Category category = objectMapper.readValue(resource.getInputStream(), Category.class);
            return recipeServerCommunicator.postCategory(category);

        } catch (IOException e) {
            logger.error("Can not process resource: " + resource.getFilename(), e);
        }
        return null;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setRecipeServerCommunicator(RecipeServerCommunicator recipeServerCommunicator) {
        this.recipeServerCommunicator = recipeServerCommunicator;
    }
}
