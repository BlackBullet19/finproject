package org.finproject.service;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.finproject.entity.Document;
import org.finproject.entity.ProgramUser;
import org.finproject.enumeration.DocumentStatus;
import org.finproject.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserService userService;

    @Override
    public void registerDocument(Exchange exchange) {
        Document document = new Document();
        Message message = exchange.getIn();
        document.setFileName(message.getHeader("CamelFileName", String.class));
        Long timestamp = message.getHeader("CamelMessageTimestamp", Long.class);
        document.setDate(new Date(timestamp));
        Long senderId = message.getHeader("SenderId", Long.class);
        ProgramUser user = userService.getUser(senderId);
        document.setUploadedBy(user);
        DocumentStatus status = message.getHeader("Status", DocumentStatus.class);
        document.setStatus(status.toString());
        Long documentNumber = message.getHeader("DocumentNumber", Long.class);
        document.setDocumentNumber(documentNumber);
        String comment = message.getHeader("Comment", String.class);
        document.setComment(comment);
        String body = message.getBody(String.class);
        document.setContent(body);
        Long sum = message.getHeader("Sum", Long.class);
        document.setSum(sum);
        documentRepository.save(document);
    }

    @Override
    public List<Document> list() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> listAllByUserId(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("Entered value can not be less than or 0");
        }
        ProgramUser user = userService.getUser(userId);
        List<Document> allDocumentsByUserId = documentRepository.findAllByUploadedBy_UserId(user.getUserId());
        if (allDocumentsByUserId.isEmpty()) {
            throw new NoSuchElementException("For user with ID " + userId + " documents not found");
        }
        return allDocumentsByUserId;
    }

    @Override
    public Document getByDocumentIdAndUserId(long documentId, long userId) {
        if (userId < 1 || documentId < 1) {
            throw new IllegalArgumentException("Entered value can not be less than or 0");
        }
        ProgramUser user = userService.getUser(userId);
        Document document = documentRepository.findByIdAndUploadedBy_UserId(documentId, userId);
        if (document == null) {
            throw new NoSuchElementException
                    ("For user with ID " + userId + " document with ID " + documentId + " not found");
        }
        return document;
    }

    @Override
    public void deleteByDocumentIdAndUserId(long documentId, long userId) {
        Document document = getByDocumentIdAndUserId(documentId, userId);
        documentRepository.delete(document);
    }
}

