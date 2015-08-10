package de.jd.recipeWebsite;


import de.jd.entities.CategoryImpl;
import de.jd.urls.CategoryServerUrls;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class CategoryMainHandler implements MainHandler {

    private CategoryServerUrls categoryServerUrls;
    private RestTemplate restTemplate;

    @Override
    public boolean canHandle(String context, String urlParam) {
        return context.equals("categoryMain");
    }

    @Override
    public ModelAndView handle(String urlPararm) {
        String categoryUrl = categoryServerUrls.getCategory(urlPararm);
        CategoryImpl category = restTemplate.getForObject(categoryUrl, CategoryImpl.class);
        ModelAndView includeCategory = new ModelAndView("includeCategory");
        includeCategory.addObject("category", category);
        return includeCategory;
    }

    public void setCategoryServerUrls(CategoryServerUrls categoryServerUrls) {
        this.categoryServerUrls = categoryServerUrls;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
