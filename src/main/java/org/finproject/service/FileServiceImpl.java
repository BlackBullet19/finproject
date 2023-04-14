package org.finproject.service;


import org.finproject.dto.FileDto;
import org.finproject.entity.FileEntity;
import org.finproject.model.FileCache;
import org.finproject.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FileServiceImpl  implements FileService {

    private static final LinkedList<FileCache> FILE_CACHES = new LinkedList<>();

    private static final int CACHE_CAPACITY = 10;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileConverter fileConverter;

    @Override
    public FileDto getFile(Long documentId) {
        int index = isPresentInCache(documentId);
        if(index >= 0) {
            FileCache fileCache = FILE_CACHES.get(index);
            FILE_CACHES.remove(fileCache);
            FILE_CACHES.addFirst(fileCache);
            return fileCache.getFileDto();
        }

        FileEntity entity = fileRepository.findById(documentId).get();
        FileDto fileDto = fileConverter.toDto(entity);

        if(FILE_CACHES.size()>= CACHE_CAPACITY){
            FILE_CACHES.removeLast();
        }

        FILE_CACHES.addFirst(new FileCache(documentId, fileDto));

        return fileDto;
    }

    private int isPresentInCache(Long documentId) {
        return FILE_CACHES.indexOf(new FileCache(documentId, null));
    }

    public List<FileCache> getCache() {
        return FILE_CACHES;
    }
}
