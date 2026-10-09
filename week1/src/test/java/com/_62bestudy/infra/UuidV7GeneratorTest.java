package com._62bestudy.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UuidV7GeneratorTest {

    @Test
    void uuid_v7을_생성한다() {
        var generator = new UuidV7Generator();

        var id = UUID.fromString(generator.generate());

        assertEquals(7, id.version());
        assertEquals(2, id.variant());
    }
}
