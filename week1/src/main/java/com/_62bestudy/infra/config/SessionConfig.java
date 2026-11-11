package com._62bestudy.infra.config;

import com._62bestudy.presentation.config.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SessionProperties.class)
public class SessionConfig {
}
