package de.jd.server.demodata.handler;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public class ImageResolver implements ResourceLoaderAware {
    private Logger logger = LoggerFactory.getLogger(ImageResolver.class);
    private ResourceLoader resourceLoader;

    public String resolveImage(String lookupString) {
        Resource resource = resourceLoader.getResource(lookupString);
        try {
            return Base64.encodeBase64String(FileUtils.readFileToByteArray(resource.getFile()));
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
