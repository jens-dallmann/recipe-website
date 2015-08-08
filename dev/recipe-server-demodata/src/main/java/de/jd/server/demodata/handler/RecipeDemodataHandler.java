package de.jd.server.demodata.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jd.entities.Recipe;
import de.jd.server.demodata.communicator.RecipeServerCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class RecipeDemodataHandler implements DemodataHandler {

    private Logger logger = LoggerFactory.getLogger(RecipeDemodataHandler.class);
    private ObjectMapper objectMapper;
    private RecipeServerCommunicator recipeServerCommunicator;

    @Override
    public HandlerResult handle(Resource resource) {
        try {
            Recipe recipe = objectMapper.readValue(resource.getInputStream(), Recipe.class);
            return recipeServerCommunicator.postRecipe(recipe);
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
