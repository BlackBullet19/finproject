package org.finproject.controller;

import org.finproject.dto.FileDto;
import org.finproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<FileDto> getFile(@PathVariable ("id") Long id) {
        FileDto file = fileService.getFile(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
}
