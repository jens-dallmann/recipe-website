package de.jd.recipeWebsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private List<MainHandler> mainHandlers;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("isIncluded", false);
        return modelAndView;
    }

    @RequestMapping("/main/category/{categoryId}")
    public ModelAndView includeCategory(@PathVariable("categoryId") String categoryId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("context", "categoryMain");
        modelAndView.addObject("urlParam", categoryId);
        modelAndView.addObject("isIncluded", true);
        return modelAndView;
    }

    @RequestMapping("/main/recipe/{recipeId}")
    public ModelAndView includeRecipe(@PathVariable("recipeId") String recipeId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("context", "recipeMain");
        modelAndView.addObject("urlParam", recipeId);
        modelAndView.addObject("isIncluded", true);
        return modelAndView;
    }

    @RequestMapping("/main/include/{context}")
    public ModelAndView includeMain(@PathVariable("context") String context,
                                    @RequestParam("urlParam") String urlParam) {
        for (MainHandler mainHandler : mainHandlers) {
            if (mainHandler.canHandle(context, urlParam)) {
                ModelAndView handle = mainHandler.handle(urlParam);
                handle.addObject("isIncluded", true);
                return handle;
            }
        }
        return new ModelAndView("index");
    }

    public void setMainHandlers(List<MainHandler> mainHandlers) {
        this.mainHandlers = mainHandlers;
    }
}
