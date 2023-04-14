package org.finproject.configuration;

import org.finproject.processors.FileProcessor;
import org.finproject.processors.StorageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinConfiguration {

    @Bean
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

    @Bean
    public StorageProcessor storageProcessor() {return new StorageProcessor();}
}
