package org.sehkah.ddonbrutforcergui.prng;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SeededXorshift128NativeTest {
    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

    @Test
    void testNextRandWithSeedContainsKeyStream61() {
        SeededXorshift128.init(4921);
        int keyDepth = 768;
        char[] keyBuffer = new char[keyDepth];
        for (int i = 0; i < keyDepth; i++) {
            keyBuffer[i] = alphabet[(int) (SeededXorshift128.nextRand() & 63)];
        }
        String keyRange = new String(keyBuffer);
        System.out.println(keyRange);
        assertTrue(keyRange.contains("UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-"));
    }
}