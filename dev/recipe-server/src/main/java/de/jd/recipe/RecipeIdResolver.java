package de.jd.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecipeIdResolver {

    public static final String ID_PREFIX = "recipe-";
    private final static Logger LOG = LoggerFactory.getLogger(RecipeIdResolver.class);

    public static int toInternalId(String externalId) {
        if (externalId.startsWith(ID_PREFIX)) {
            String internalId = externalId.replaceFirst(ID_PREFIX, "");
            LOG.info("Converted {} to internal id \"{}\"", externalId, internalId);
            return Integer.valueOf(internalId);
        }
        LOG.info("{} is not prefixed with the external id prefix. Nothing to do here.");

        return Integer.valueOf(externalId);
    }

    public static String toExternalId(int i) {
        return String.format("%s%s", ID_PREFIX, i);
    }
}
