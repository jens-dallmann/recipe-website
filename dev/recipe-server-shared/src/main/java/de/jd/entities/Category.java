package de.jd.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as = CategoryImpl.class)
public interface Category {

    void setRecipes(List<Recipe> recipes);
    List<Recipe> getRecipes();

    void setTitle(String title);
    String getTitle();

    void setId(String id);
    String getId();

}
