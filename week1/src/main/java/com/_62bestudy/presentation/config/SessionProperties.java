package com._62bestudy.presentation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "session")
public record SessionProperties(
        String cookieName,
        String headerName,
        String sessionIdAttribute,
        String currentUserAttribute
) {
}
