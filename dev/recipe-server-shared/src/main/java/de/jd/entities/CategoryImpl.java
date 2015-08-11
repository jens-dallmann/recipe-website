package de.jd.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryImpl implements Category {
    @JsonIgnore
    private Map<String, Object> properties;
    private String text;

    public CategoryImpl() {
        properties = new HashMap<>();
    }

    public CategoryImpl(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        properties.put("recipes",recipes);
    }

    @Override
    public List<Recipe> getRecipes() {
        return (List<Recipe>) properties.get("recipes");
    }

    @Override
    public void setTitle(String title) {
        properties.put("title", title);
    }

    @Override
    public String getTitle() {
        return (String) properties.get("title");
    }

    @Override
    public void setId(String id) {
        properties.put("id", id);
    }

    @Override
    public String getId() {
        return (String) properties.get("id");
    }

    @Override
    public void setText(String text) {
        properties.put("text",text);
    }

    @Override
    public String getText() {
        return (String) properties.get("text");
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
