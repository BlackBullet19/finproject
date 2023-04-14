package org.finproject.service;

import org.apache.camel.Exchange;
import org.finproject.entity.Document;

import java.util.List;

public interface DocumentService {

    void registerDocument(Exchange exchange);

    List<Document> list();

    List<Document> listAllByUserId(long userId);

    Document getByDocumentIdAndUserId(long documentId, long userId);

    void deleteByDocumentIdAndUserId(long documentId, long userId);
}
