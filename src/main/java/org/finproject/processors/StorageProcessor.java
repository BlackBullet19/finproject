package org.finproject.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.finproject.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

public class StorageProcessor implements Processor {

    @Autowired
    private DocumentService documentService;

    @Override
    public void process(Exchange exchange) throws Exception {
        if(exchange.getIn().getHeader("SKIP_DOCUMENT", Boolean.class)){
            return;
        }
        documentService.registerDocument(exchange);
    }
}
