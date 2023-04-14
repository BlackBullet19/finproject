package org.finproject.service;

import org.finproject.dto.FileDto;

public interface FileService {

   FileDto getFile(Long documentId);
}
