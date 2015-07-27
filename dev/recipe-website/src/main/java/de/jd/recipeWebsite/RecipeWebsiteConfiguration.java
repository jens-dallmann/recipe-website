package de.jd.recipeWebsite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class RecipeWebsiteConfiguration {

    @Bean (name = "recipeServerUrls")
    public RecipeServerUrls recipeServerUrls(Environment environment) {
        RecipeServerUrls recipeServerUrls = new RecipeServerUrls();
        String serverUrls = environment.getProperty("recipe.service.base.url");
        recipeServerUrls.setRecipeServerBaseUrl(serverUrls);
        return recipeServerUrls;
    }

    @Bean (name = "restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
