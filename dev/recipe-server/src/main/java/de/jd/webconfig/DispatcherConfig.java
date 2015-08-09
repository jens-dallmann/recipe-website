package de.jd.webconfig;

import de.jd.recipe.RequestHandlerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import(RequestHandlerConfiguration.class)
public class DispatcherConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("mediaType")
                .defaultContentType(MediaType.APPLICATION_JSON)
                .useJaf(false).ignoreAcceptHeader(true)
                .mediaType(".json", MediaType.APPLICATION_JSON)
                .mediaType(".xml", MediaType.APPLICATION_XML)
                .favorPathExtension(false);
    }
}
