package de.jd.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Recipe implements Serializable {
    @JsonIgnore
    private Map<String, Object> properties;

    public Recipe() {
        properties = new HashMap<String, Object>();
    }

    public Recipe(Map<String, Object> properties) {
        this.properties = properties;
    }

    public int getId() {
        return (Integer) properties.get("id");
    }

    public void setId(int id) {
        properties.put("id", id);
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
