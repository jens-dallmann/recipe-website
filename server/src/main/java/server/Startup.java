package server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import server.recipe.RecipeConfiguration;

@ComponentScan
@Configuration
public class Startup {
    public static void main(String[] args) {
        System.setProperty("server.port", "8082");
        SpringApplication.run(RecipeConfiguration.class, args);
    }

}
