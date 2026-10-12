package com._62bestudy.infra;

import com._62bestudy.application.PostIdGenerator;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UuidGenerator implements PostIdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
