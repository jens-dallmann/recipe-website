package de.jd.status;


import de.jd.entities.Recipe;

import java.io.Serializable;

public class OneRecipeStatusResponse implements Serializable{

    private StatusResponse status;

    private Recipe recipe;

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
