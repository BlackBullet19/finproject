package org.finproject;

import org.finproject.configuration.FinConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FinConfiguration.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
