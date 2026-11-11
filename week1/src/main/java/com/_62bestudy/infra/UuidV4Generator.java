package com._62bestudy.infra;

import com._62bestudy.application.IdGenerator;
import java.util.UUID;

public class UuidV4Generator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
