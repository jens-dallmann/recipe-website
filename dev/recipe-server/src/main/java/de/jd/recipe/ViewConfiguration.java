package de.jd.recipe;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;

@Configuration
@EnableWebMvc
public class ViewConfiguration extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(ViewConfiguration.class);

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("mediaType")
                .defaultContentType(MediaType.APPLICATION_JSON)
                .useJaf(false).ignoreAcceptHeader(true)
                .mediaType(".json", MediaType.APPLICATION_JSON)
                .mediaType(".xml", MediaType.APPLICATION_XML)
                .favorPathExtension(false);
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            log.error("Unknown Host Exception during creating MongoClient");
        }
        return mongoClient;
    }

    @Bean
    public RecipeDao recipeDao(MongoClient mongoClient) {
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.setMongoClient(mongoClient);
        return recipeDao;
    }
}
