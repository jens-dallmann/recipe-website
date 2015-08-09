package de.jd.server.demodata.service;

import de.jd.server.demodata.handler.HandlerResult;
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
            for (Resource resource : resources) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(resource.getInputStream()));
                String tagName = doc.getDocumentElement().getTagName();
                DemodataHandler demodataHandler = demodataHandlers.get(tagName);
                if (demodataHandler != null) {
                    HandlerResult handle = demodataHandler.handle(resource);
                    if(handle != null && !handle.isSuccessfull()){
                        logger.warn("Resource not posted: "+resource.getFilename());
                    }
                    else {
                        logger.info("Resource loading successfull"+resource.getFilename());
                    }
                } else {
                    logger.warn("No demodata handler found for tagname: " + tagName);
                }
            }
        } catch (IOException e) {
            logger.error("Can not load resource: " + e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            logger.error("Parse error: "+e.getMessage(), e);
        } catch (SAXException e) {
           logger.error("SaxException while opening xml resource", e);
        }
        return true;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        this.resourceLoader = resourceLoader;
    }

    public void setDemodataHandlers(Map<String, DemodataHandler> demodataHandlers) {
        this.demodataHandlers = demodataHandlers;
    }
}
