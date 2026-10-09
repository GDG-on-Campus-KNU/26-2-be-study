package com._62bestudy.infra.config;

import com._62bestudy.application.IdGenerator;
import com._62bestudy.infra.UuidV7Generator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfig {

    @Bean
    public IdGenerator idGenerator() {
        return new UuidV7Generator();
    }
}
