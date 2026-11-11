package com._62bestudy.infra;

import com._62bestudy.application.IdGenerator;
import java.security.SecureRandom;
import java.util.UUID;

public class UuidV7Generator implements IdGenerator {

    private static final long TIMESTAMP_MASK = 0x0000FFFFFFFFFFFFL;
    private static final long RANDOM_A_MASK = 0x0000000000000FFFL;
    private static final long VERSION_7_BITS = 0x0000000000007000L;
    private static final long VARIANT_MASK = 0x3FFFFFFFFFFFFFFFL;
    private static final long VARIANT_BITS = 0x8000000000000000L;

    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate() {
        var timestampMillis = System.currentTimeMillis() & TIMESTAMP_MASK;
        var randomA = random.nextLong() & RANDOM_A_MASK;

        var mostSignificantBits = (timestampMillis << 16)
                | VERSION_7_BITS
                | randomA;
        var leastSignificantBits = (random.nextLong() & VARIANT_MASK)
                | VARIANT_BITS;

        return new UUID(mostSignificantBits, leastSignificantBits).toString();
    }
}
