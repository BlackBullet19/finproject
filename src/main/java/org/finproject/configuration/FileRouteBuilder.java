package org.finproject.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.finproject.entity.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRouteBuilder extends RouteBuilder {

    @Value("${finproject.path}")
    private String pathToFile;

    @Override
    public void configure() throws Exception {
        String path = "file://" + pathToFile + "?delete=true";

        String url = "activemq:queue:FILE.OUTPUT";

        from(path)
                .routeId("fileInputRoute")
                .process("fileProcessor")
                .process("storageProcessor")
                .to(url)
                .stop();

        rest("/documents")
                .routeId("fileFromRest")
                .post("/{userId}")
                .outType(Document.class)
                .to(url);

    }
}
