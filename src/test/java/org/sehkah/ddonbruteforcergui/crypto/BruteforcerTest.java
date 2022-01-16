package org.sehkah.ddonbruteforcergui.crypto;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteforcerTest {
    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream61Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("F136F3392042F4CF3BF6B9CD6D79DF94");
        String key = Bruteforcer.bruteforceRange(4921, 4922, 652 + 32 + 1, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream61Session1ServerSelect() throws Exception {
        byte[] ciphertext = Hex.decode("eafa5971990de6f471dcc66a480b01ff");
        String key = Bruteforcer.bruteforceRange(4921, 4922, 2043 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("YdT7cIV9UOSxS04T764DHn2PJL-QoJRH", key);
    }

    /**
     * TODO: not reproducible -> nothilvien#1337
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream61Session1Game() throws Exception {
        byte[] ciphertext = Hex.decode("fa170821ef6ffc19bce4a82e63ec9208");
        String key = Bruteforcer.bruteforceRange(4921, 4922, 5000, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("q9p2wKk5jOiYuCkqT0nN6giLQa-qTmuz", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream13Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("af192a98535d0d5cb2dcfc6aff415104");
        String key = Bruteforcer.bruteforceRange(2344, 2345, 237 + 32 + 1, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("Vp1QAhucAorAttbmeBc7FiHI3jc6R11q", key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session2Login() throws Exception {
        byte[] ciphertext = Hex.decode("fdb5bd9ca85262218760b7958f171019");
        String key = Bruteforcer.bruteforceRange(2344, 2345, 100000000, ciphertext, Hex.decode("010000023400000000"));
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session2ServerSelect() throws Exception {
        byte[] ciphertext = Hex.decode("2273c58f0c47110f95d02f37658fd86c");
        String key = Bruteforcer.bruteforceRange(2344, 2345, 100000000, ciphertext, Hex.decode("010000023400000000"));
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session3Login() throws Exception {
        byte[] ciphertext = Hex.decode("bbbc4b6a40a1cd6bb5c26fd0c66a850f");
        String key = Bruteforcer.bruteforceRange(0, 20000, 10000, ciphertext, Hex.decode("010000023400000000"));
        assertNotNull(key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("06d8d384a2d09b2929ed24156692e2d3\n");
        String key = Bruteforcer.bruteforceRange(4990, 4991, 3799 + 32 + 1, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("J2g4pE2_heyqIAengWy0N6D1SEklxz8I", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1ServerSelect() throws Exception {
        byte[] ciphertext = Hex.decode("179522096dfda401fa4183a4c6b6058b");
        String key = Bruteforcer.bruteforceRange(4990, 4991, 5204 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("N5ee3Ra0KBZ-UVELDmKRnhe8wqWvWEm7", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1Game() throws Exception {
        byte[] ciphertext = Hex.decode("672b402620bbb068a9b673010243de55");
        String key = Bruteforcer.bruteforceRange(4990, 4991, 5558 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("hkNnBgyXz40de6kX3vkmAWGvatldB-Pp", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("F5B9DD7D31CF79C069C36657698F000A");
        String key = Bruteforcer.bruteforceRange(5024, 5026, 2469 + 32 + 1, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("xjHuxmupDxXebJStnsS2gIixY2c4fgUp", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86ServerSelect() throws Exception {
        byte[] ciphertext = Hex.decode("cbeb0b9002cf91c114688035892f282c");
        String key = Bruteforcer.bruteforceRange(5024, 5026, 4097 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("E-pksmlfoN6r3_TIBDUPdjpB9gVFZQzu", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86Game() throws Exception {
        byte[] ciphertext = Hex.decode("89e5b32f75c5648b48615429caf9d763");
        String key = Bruteforcer.bruteforceRange(5024, 5026, 8097 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream16Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("7C816029E153C304714DEFFD9FDFD4A8");
        String key = Bruteforcer.bruteforceRange(0, 10000, 10000, ciphertext, Hex.decode("010000023400000000"));
        assertNotNull(key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1Login() throws Exception {
        byte[] ciphertext = Hex.decode("371D3283A0A09D14BB35CBB004E5F707");
        String key = Bruteforcer.bruteforceRange(2393, 2394, 666 + 32 + 1, ciphertext, Hex.decode("010000023400000000"));
        assertEquals("Bgun9EAmPhyxYs5z_6B6_ZYOfe1Fzzt4", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1ServerSelect() throws Exception {
        byte[] ciphertext = Hex.decode("0BFABE1B0F3D126D98C923DD0A7C2A2D");
        String key = Bruteforcer.bruteforceRange(2393, 2394, 1961 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("NvIY0Jci4iVmqs67Kgnm6XcMvYg03wI4", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1Game() throws Exception {
        byte[] ciphertext = Hex.decode("0189087745144A6E6C39E52B223A19C9");
        String key = Bruteforcer.bruteforceRange(2393, 2394, 2309 + 32 + 1, ciphertext, Hex.decode("2C0000023400000000"));
        assertEquals("s5-2GHMs5pv_7-K17YW79i8HFKFX9fa6", key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream3Session2Login() throws Exception {
        byte[] ciphertext = Hex.decode("0189087745144A6E6C39E52B223A19C9");
        String key = Bruteforcer.bruteforceRange(0, 10000, 10000, ciphertext, Hex.decode("010000023400000000"));
        assertNotNull(key);
    }
}