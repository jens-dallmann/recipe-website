package de.jd.server.demodata.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jd.entities.Recipe;
import de.jd.server.demodata.communicator.RecipeServerCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDemodataHandler implements DemodataHandler {

    private Logger logger = LoggerFactory.getLogger(RecipeDemodataHandler.class);
    private ObjectMapper objectMapper;
    private RecipeServerCommunicator recipeServerCommunicator;
    private ImageResolver imageResolver;

    @Override
    public HandlerResult handle(Resource resource) {
        try {
            Recipe recipe = objectMapper.readValue(resource.getInputStream(), Recipe.class);
            List<String> images = recipe.getImages();
            if(!CollectionUtils.isEmpty(images)) {
                List<String> newImages = new ArrayList<>();
                for (String image : images) {
                    String imageContent = imageResolver.resolveImage(image);
                    newImages.add(imageContent);
                }
                recipe.setImages(newImages);
            }
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

    public void setImageResolver(ImageResolver imageResolver) {
        this.imageResolver = imageResolver;
    }
}
