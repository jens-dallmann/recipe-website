package de.jd.server.demodata.handler;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Base64;

public class ImageResolver implements ResourceLoaderAware {
    private Logger logger = LoggerFactory.getLogger(ImageResolver.class);
    private ResourceLoader resourceLoader;

    public String resolveImage(String lookupString) {
        Resource resource = resourceLoader.getResource(lookupString);
        try {

            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(FileUtils.readFileToByteArray(resource.getFile()));
        } catch (IOException e) {
            logger.error("Can't read picture: " + resource.getFilename(), e);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
