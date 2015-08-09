package de.jd.recipe.category;

import de.jd.entities.Category;
import de.jd.entities.CategoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryService {

    private final static Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private CategoryDao categoryDao;

    @RequestMapping(value = "/category/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Category category(@PathVariable String id) {
        logger.info("Try to get category");
        CategoryImpl queryObject = new CategoryImpl();
        queryObject.setId(id);
        Category category = categoryDao.findCategory(queryObject);
        return category;
    }

    @RequestMapping(value = "/category/post", consumes="application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public void post(@RequestBody CategoryImpl recipe) {
        logger.info("Try to post category");

        categoryDao.post(recipe);
    }

    @RequestMapping(value = "/category", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryImpl> getAll() {
        logger.info("Try to get all category");

        return categoryDao.findAll();
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
