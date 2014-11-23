package de.jd.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.jd.entities.Recipe;

public class OneRecipeStatusResponseBuilder {

    private OneRecipeStatusResponse oneRecipeStatusResponse;

    public OneRecipeStatusResponseBuilder() {
        oneRecipeStatusResponse = new OneRecipeStatusResponse();
        StatusResponse statusResponse = new StatusResponse();
        oneRecipeStatusResponse.setStatus(statusResponse);
        oneRecipeStatusResponse.getStatus().setSuccessfull(true);
    }

    public OneRecipeStatusResponseBuilder withRecipe(Recipe withRecipe) {
        oneRecipeStatusResponse.setRecipe(withRecipe);
        return this;
    }

    public OneRecipeStatusResponseBuilder withError(String key, String message) {
        oneRecipeStatusResponse.getStatus().setSuccessfull(false);
        oneRecipeStatusResponse.getStatus().setErrorMsg(message);
        oneRecipeStatusResponse.getStatus().setErrorMsgKey(key);
        return this;
    }

    public OneRecipeStatusResponse build() {
        return oneRecipeStatusResponse;
    }
}
