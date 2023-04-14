package org.finproject.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.finproject.FinHeader;
import org.finproject.enumeration.DocumentStatus;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;

public class FileProcessor implements Processor {

    @Value("${admin.userId}")
    private long ADMIN_ID;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();
        if (!message.getHeader("CamelFileName", String.class).endsWith(".txt")) {
            message.setHeader(FinHeader.SKIP_DOCUMENT, Boolean.TRUE);
        } else {
            message.setHeader(FinHeader.SKIP_DOCUMENT, Boolean.FALSE);
        }
        if (message.getHeader("SenderId") == null) {
            message.setHeader("SenderId", ADMIN_ID);
        }
        message.setHeader("Status", DocumentStatus.INCOMING);
        if(message.getHeader("DocumentNumber") == null){
            message.setHeader("DocumentNumber",(int) (Math.random() * 1000000000));
        }
        if(message.getHeader("Sum") == null) {
            message.setHeader("Sum", 0);
        }
        if(message.getHeader("Comment") == null){
            message.setHeader("Comment", "No comments");
        }
    }
}
