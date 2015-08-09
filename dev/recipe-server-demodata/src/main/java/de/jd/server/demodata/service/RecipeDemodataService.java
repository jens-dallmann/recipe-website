package de.jd.server.demodata.service;

import de.jd.server.demodata.handler.DemodataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDemodataService implements DemodataService, ResourceLoaderAware {

    private ResourceLoader resourceLoader;
    private Logger logger = LoggerFactory.getLogger(RecipeDemodataService.class);
    private Map<String, DemodataHandler> demodataHandlers;

    @Override
    public boolean createRecipes() {
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
        try {
            Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath:/data/**/*.xml");

            Map<String, List<Resource>> sortedResources = filterByTagname(resources);
            List<Resource> recipes = sortedResources.get("recipe");
            DemodataHandler recipeHandler = demodataHandlers.get("recipe");
            for (Resource recipe : recipes) {
                recipeHandler.handle(recipe);
            }

            List<Resource> categories = sortedResources.get("category");
            DemodataHandler categoryHandler = demodataHandlers.get("category");

            for(Resource category : categories) {
                categoryHandler.handle(category);
            }
        } catch (IOException e) {
            logger.error("Can not load resource: " + e.getMessage(), e);
        }
        return true;
    }

    private Map<String, List<Resource>> filterByTagname(Resource[] resources) {
        Map<String, List<Resource>> resourceSorted = new HashMap<>();
        for (Resource resource : resources) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(resource.getInputStream()));
                String tagName = doc.getDocumentElement().getTagName();
                if(resourceSorted.get(tagName) == null) {
                    resourceSorted.put(tagName, new ArrayList<Resource>());
                }
                resourceSorted.get(tagName).add(resource);

            } catch (ParserConfigurationException e) {
                logger.error("Parse exception",e);
            } catch (SAXException e) {
                logger.error("SaxException while opening xml resource", e);
            } catch (IOException e) {
                logger.error("Can not load resource: " + e.getMessage(), e);

            }
        }
        return resourceSorted;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setDemodataHandlers(Map<String, DemodataHandler> demodataHandlers) {
        this.demodataHandlers = demodataHandlers;
    }
}
