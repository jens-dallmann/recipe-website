package de.jd.recipe;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeService {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(RecipeService.class);

    @RequestMapping(value = "/recipe", produces = "application/json")
    public @ResponseBody String recipe() {
        Recipe recipe = new Recipe();
        LOG.warn("WUHU");
        recipe.setTitle("anyTitle");
        recipe.setId(0);
        return "{abc:dasdf}";
    }
}
