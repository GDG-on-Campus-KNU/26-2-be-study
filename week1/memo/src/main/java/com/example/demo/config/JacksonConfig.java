package com.example.demo.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.cfg.CoercionAction;
import tools.jackson.databind.cfg.CoercionInputShape;
import tools.jackson.databind.type.LogicalType;

@Configuration
public class JacksonConfig {

    // Jackson은 기본적으로 {"content": 123}의 숫자 123을 문자열 "123"으로 바꿔서 받아준다.
    // 문자열 필드에 숫자·불리언이 오면 변환하지 않고 실패(400)하도록 막는다.
    @Bean
    public JsonMapperBuilderCustomizer strictStringCustomizer(){
        return builder -> builder.withCoercionConfig(LogicalType.Textual, config -> config
                .setCoercion(CoercionInputShape.Integer, CoercionAction.Fail)
                .setCoercion(CoercionInputShape.Float, CoercionAction.Fail)
                .setCoercion(CoercionInputShape.Boolean, CoercionAction.Fail));
    }
}
