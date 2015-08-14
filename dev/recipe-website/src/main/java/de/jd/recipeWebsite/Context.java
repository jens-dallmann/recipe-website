package de.jd.recipeWebsite;

import de.jd.entities.Category;

public class Context {
    private Category category;
    private Object entity;
    private String contextName;
    private boolean included;

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Object getEntity() {
        return entity;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public String getContextName() {
        return contextName;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean isIncluded) {
        this.included = isIncluded;
    }
}
