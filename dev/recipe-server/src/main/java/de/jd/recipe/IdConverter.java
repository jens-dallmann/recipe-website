package de.jd.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdConverter {
    private String idPrefix = "";
    private final static Logger LOG = LoggerFactory.getLogger(IdConverter.class);

    public int toInternalId(String externalId) {
        if (externalId.startsWith(idPrefix)) {
            String internalId = externalId.replaceFirst(idPrefix, "");
            LOG.info("Converted {} to internal id \"{}\"", externalId, internalId);
            return Integer.valueOf(internalId);
        }
        LOG.info("{} is not prefixed with the external id prefix. Nothing to do here.");

        return Integer.valueOf(externalId);
    }

    public String toExternalId(int i) {
        return String.format("%s%s", idPrefix, i);
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }
}
