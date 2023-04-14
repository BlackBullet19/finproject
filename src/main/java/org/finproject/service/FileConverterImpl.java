package org.finproject.service;

import org.finproject.dto.FileDto;
import org.finproject.entity.FileEntity;
import org.springframework.stereotype.Service;

@Service
public class FileConverterImpl implements FileConverter {

    @Override
    public FileDto toDto(FileEntity fileEntity) {
        return null;
    }

    @Override
    public FileEntity fromDto(FileDto fileDto) {
        return null;
    }
}
