package de.jd.recipe;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class ViewConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("mediaType")
                .defaultContentType(MediaType.APPLICATION_JSON)
                .useJaf(false).ignoreAcceptHeader(true)
                .mediaType(".json",MediaType.APPLICATION_JSON)
                .mediaType(".xml",MediaType.APPLICATION_XML);
    }
}
