package de.jd.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(as = RecipeImpl.class)
public interface Recipe extends Serializable {

    String getId();
    void setId(String id);

    void setImages(List<String> images);
    List<String> getImages();

    String getTitle();
    void setTitle(String title);

    String getText();
    void setText(String text);
}
