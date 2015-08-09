package de.jd.recipe;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class MongoConfiguration {
    private Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Bean(name = "mongoClient")
    public MongoClient mongoClient() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            logger.error("Unknown Host Exception during creating MongoClient");
        }
        return mongoClient;
    }
}
