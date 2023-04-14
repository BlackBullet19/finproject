package org.finproject.service;

import org.finproject.dto.FileDto;
import org.finproject.entity.FileEntity;

public interface FileConverter {

    FileDto toDto(FileEntity fileEntity);

    FileEntity fromDto(FileDto fileDto);
}
