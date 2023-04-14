package org.finproject.controller;

import org.finproject.entity.Document;
import org.finproject.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(documentService.list(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> listAllByUserId(@PathVariable(name = "userId") long userId) {
        try {
            return new ResponseEntity<>(documentService.listAllByUserId(userId), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{userId}/{documentId}")
    public ResponseEntity<?> getDocumentByUserIdAndDocumentId
            (@PathVariable(name = "userId") long userId, @PathVariable(name = "documentId") long documentId) {
        try {
            return new ResponseEntity<>(documentService.getByDocumentIdAndUserId(documentId, userId), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{userId}/{documentId}")
    public ResponseEntity<?> deleteDocumentByUserIdAndDocumentId
            (@PathVariable(name = "userId") long userId, @PathVariable(name = "documentId") long documentId) {
        try {
            documentService.deleteByDocumentIdAndUserId(documentId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> registerDocumentFromUser
            (@PathVariable(name = "userId") long userId, @RequestBody Document document) {
        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
