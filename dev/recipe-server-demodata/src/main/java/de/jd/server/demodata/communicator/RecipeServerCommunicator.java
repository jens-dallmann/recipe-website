package de.jd.server.demodata.communicator;

import de.jd.entities.Category;
import de.jd.entities.Recipe;
import de.jd.server.demodata.handler.HandlerResult;

public interface RecipeServerCommunicator {
    HandlerResult postRecipe(Recipe recipe);

    HandlerResult postCategory(Category category);
}
