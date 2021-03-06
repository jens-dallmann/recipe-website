package de.jd.server.demodata;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jd.server.demodata.communicator.RecipeServerCommunicator;
import de.jd.server.demodata.communicator.RecipeServerCommunicatorImpl;
import de.jd.server.demodata.handler.CategoryDemodataHandler;
import de.jd.server.demodata.handler.DemodataHandler;
import de.jd.server.demodata.handler.ImageResolver;
import de.jd.server.demodata.handler.RecipeDemodataHandler;
import de.jd.server.demodata.service.DemodataService;
import de.jd.server.demodata.service.RecipeDemodataService;
import de.jd.urls.CategoryServerUrls;
import de.jd.urls.RecipeServerUrls;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Configuration
@PropertySource("classpath:application.properties")
public class DemodataConfiguration {

    @Bean (name = "recipeServerUrls")
    public RecipeServerUrls recipeServerUrls(Environment environment) {
        RecipeServerUrls recipeServerUrls = new RecipeServerUrls();
        String serverUrls = environment.getProperty("recipe.service.base.url");
        recipeServerUrls.setRecipeServerBaseUrl(serverUrls);
        return recipeServerUrls;
    }
    @Bean (name = "categoryServerUrls")
    public CategoryServerUrls categoryServerUrls(Environment environment) {
        CategoryServerUrls categoryServerUrls = new CategoryServerUrls();
        String serverUrls = environment.getProperty("recipe.service.base.url");
        categoryServerUrls.setCategoryServerBaseUrl(serverUrls);
        return categoryServerUrls;
    }

    @Bean(name = "xmlObjectMapper")
    public ObjectMapper xmlObjectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(true).build();
    }

    @Bean(name = "demodataService")
    public DemodataService demodataService(@Qualifier("recipeHandler") DemodataHandler handler,
                                           @Qualifier("categoryHandler") DemodataHandler categoryHandler) {
        RecipeDemodataService recipeDemodataService = new RecipeDemodataService();
        HashMap<String, DemodataHandler> demodataHandlers = new HashMap<>();
        demodataHandlers.put("recipe", handler);
        demodataHandlers.put("category", categoryHandler);
        recipeDemodataService.setDemodataHandlers(demodataHandlers);
        return recipeDemodataService;
    }

    @Bean (name = "restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    @Bean (name = "demodataController")
    public DemodataController demodataController(@Qualifier("demodataService") DemodataService demodataService) {
        DemodataController demodataController = new DemodataController();
        demodataController.setDemodataService(demodataService);
        return demodataController;
    }

    @Bean (name = "recipeHandler")
    public RecipeDemodataHandler recipeDemodataHandler(@Qualifier("xmlObjectMapper") ObjectMapper objectMapper,
                                                       @Qualifier("recipeServerCommunicator") RecipeServerCommunicator recipeServerCommunicator,
                                                       @Qualifier("imageResolver") ImageResolver imageResolver) {
        RecipeDemodataHandler recipeDemodataHandler = new RecipeDemodataHandler();
        recipeDemodataHandler.setObjectMapper(objectMapper);
        recipeDemodataHandler.setRecipeServerCommunicator(recipeServerCommunicator);
        recipeDemodataHandler.setImageResolver(imageResolver);
        return recipeDemodataHandler;
    }


    @Bean (name = "categoryHandler")
    public CategoryDemodataHandler categoryDemodataHandler(@Qualifier("xmlObjectMapper") ObjectMapper objectMapper,
                                                       @Qualifier("recipeServerCommunicator") RecipeServerCommunicator recipeServerCommunicator,
                                                       @Qualifier("imageResolver") ImageResolver imageResolver) {
        CategoryDemodataHandler recipeDemodataHandler = new CategoryDemodataHandler();
        recipeDemodataHandler.setObjectMapper(objectMapper);
        recipeDemodataHandler.setRecipeServerCommunicator(recipeServerCommunicator);
        return recipeDemodataHandler;
    }

    @Bean (name ="recipeServerCommunicator")
    public RecipeServerCommunicator recipeServerCommunicator(@Qualifier("restTemplate") RestTemplate restTemplate,
                                                             @Qualifier("recipeServerUrls") RecipeServerUrls recipeServerUrls,
                                                             @Qualifier("categoryServerUrls") CategoryServerUrls categoryServerUrls) {
        RecipeServerCommunicatorImpl recipeServerCommunicator = new RecipeServerCommunicatorImpl();
        recipeServerCommunicator.setRestTemplate(restTemplate);
        recipeServerCommunicator.setRecipeServerUrls(recipeServerUrls);
        recipeServerCommunicator.setCategoryServerUrls(categoryServerUrls);
        return recipeServerCommunicator;
    }

    @Bean (name = "imageResolver")
    public ImageResolver imageResolver() {
        return new ImageResolver();
    }
}
