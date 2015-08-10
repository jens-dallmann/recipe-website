package de.jd.recipeWebsite;

import org.springframework.web.servlet.ModelAndView;

public interface MainHandler {

    boolean canHandle(String context, String urlParam);

    ModelAndView handle(String urlParam);
}
