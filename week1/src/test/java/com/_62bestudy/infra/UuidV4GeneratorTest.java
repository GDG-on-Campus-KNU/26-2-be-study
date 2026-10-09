package com._62bestudy.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UuidV4GeneratorTest {

    @Test
    void uuid_v4를_생성한다() {
        var generator = new UuidV4Generator();

        var id = UUID.fromString(generator.generate());

        assertEquals(4, id.version());
        assertEquals(2, id.variant());
    }
}
