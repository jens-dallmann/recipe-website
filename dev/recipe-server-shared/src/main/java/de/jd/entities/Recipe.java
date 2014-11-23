package de.jd.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(as = RecipeImpl.class)
public interface Recipe extends Serializable {

    public String getId();

    public void setId(String id);

    public void setImages(List<String> images);

    public List<String> getImages();

    public String getTitle();

    public void setTitle(String title);
}
