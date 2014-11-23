package de.jd.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.jd.entities.Recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeImpl implements Recipe {
    @JsonIgnore
    private Map<String, Object> properties;

    public RecipeImpl() {
        properties = new HashMap<String, Object>();
    }

    public RecipeImpl(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getId() {
        return (String) properties.get("id");
    }

    public void setId(String id) {
        properties.put("id", id);
    }

    public void setImages(List<String> images) {
        properties.put("images", images);
    }

    public List<String> getImages() {
        return (List<String>) properties.get("images");
    }

    public String getTitle() {
        return (String) properties.get("title");
    }

    public void setTitle(String title) {
        properties.put("title", title);
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
