package de.jd.server.demodata.handler;


import org.springframework.core.io.Resource;

public interface DemodataHandler {

    HandlerResult handle(Resource resource);

}
