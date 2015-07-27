package de.jd.webconfig;

import de.jd.recipeWebsite.RequestHandlerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@Import(RequestHandlerConfiguration.class)
public class DispatcherConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addStatusController("/test", HttpStatus.OK);
        super.addViewControllers(registry);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
        super.configureViewResolvers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        super.addResourceHandlers(registry);
    }
}
