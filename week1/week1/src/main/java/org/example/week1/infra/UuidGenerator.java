package org.example.week1.infra;

import org.example.week1.application.PostIdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements PostIdGenerator {

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
