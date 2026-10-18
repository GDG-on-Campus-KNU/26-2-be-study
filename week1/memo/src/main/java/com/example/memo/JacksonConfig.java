package com.example.memo;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.cfg.CoercionAction;
import tools.jackson.databind.cfg.CoercionInputShape;

@Configuration
public class JacksonConfig {

    @Bean
    public JsonMapperBuilderCustomizer strictStringCustomizer() {
        return builder -> builder.withCoercionConfig(
                String.class,
                config -> {
                    config.setCoercion(
                            CoercionInputShape.Integer,
                            CoercionAction.Fail
                    );
                    config.setCoercion(
                            CoercionInputShape.Float,
                            CoercionAction.Fail
                    );
                    config.setCoercion(
                            CoercionInputShape.Boolean,
                            CoercionAction.Fail
                    );
                }
        );
    }
}
