package com.dahada.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@ConfigurationPropertiesScan
@EnableJpaAuditing
@SpringBootApplication
public class DahadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DahadaApplication.class, args);
    }
}
