package de.jd.urls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryServerUrls {
    private static final Logger log = LoggerFactory.getLogger(CategoryServerUrls.class);

    private String categoryServerBaseUrl;

    public String getAllCategories() {
        return getCategoryServerBaseUrl() + "/category/";
    }

    public String getCategory(String id) {
        String template = getCategoryServerBaseUrl() + "/category/%s";
        return String.format(template, id);
    }

    public String postCategory() {
        return getCategoryServerBaseUrl() + "/category/post";
    }

    public String getCategoryServerBaseUrl() {
        return categoryServerBaseUrl;
    }

    public void setCategoryServerBaseUrl(String categoryServerBaseUrl) {
        log.info("Set recipe server base url: \"{}\"", categoryServerBaseUrl);
        this.categoryServerBaseUrl = categoryServerBaseUrl;
    }
}
