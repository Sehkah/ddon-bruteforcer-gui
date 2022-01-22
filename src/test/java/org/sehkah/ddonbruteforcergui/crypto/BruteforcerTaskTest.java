package org.sehkah.ddonbruteforcergui.crypto;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.sehkah.ddonbruteforcergui.model.crypto.BruteforceTask;

import static org.junit.jupiter.api.Assertions.*;

class BruteforcerTaskTest {
    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream61Session1Login() {
        String key = new BruteforceTask(4921, 652 + 32 + 1, Hex.decode("F136F3392042F4CF3BF6B9CD6D79DF94"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream61Session1ServerSelect() {
        String key = new BruteforceTask(4921, 2043 + 32 + 1, Hex.decode("eafa5971990de6f471dcc66a480b01ff"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("YdT7cIV9UOSxS04T764DHn2PJL-QoJRH", key);
    }

    /**
     * TODO: not reproducible -> nothilvien#1337
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream61Session1Game() {
        String key = new BruteforceTask(4921, 5000, Hex.decode("fa170821ef6ffc19bce4a82e63ec9208"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("q9p2wKk5jOiYuCkqT0nN6giLQa-qTmuz", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream13Session1Login() {
        String key = new BruteforceTask(2344, 237 + 32 + 1, Hex.decode("af192a98535d0d5cb2dcfc6aff415104"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("Vp1QAhucAorAttbmeBc7FiHI3jc6R11q", key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session2Login() {
        String key = new BruteforceTask(2344, 100000000, Hex.decode("fdb5bd9ca85262218760b7958f171019"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session2ServerSelect() {
        String key = new BruteforceTask(2344, 100000000, Hex.decode("2273c58f0c47110f95d02f37658fd86c"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream13Session3Login() {
        String key = new BruteforceTask(0, 10000, Hex.decode("bbbc4b6a40a1cd6bb5c26fd0c66a850f"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1Login() {
        String key = new BruteforceTask(4990, 3799 + 32 + 1, Hex.decode("06d8d384a2d09b2929ed24156692e2d3"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("J2g4pE2_heyqIAengWy0N6D1SEklxz8I", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1ServerSelect() {
        String key = new BruteforceTask(4990,  5204 + 32 + 1, Hex.decode("179522096dfda401fa4183a4c6b6058b"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("N5ee3Ra0KBZ-UVELDmKRnhe8wqWvWEm7", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream85Session1Game() {
        String key = new BruteforceTask(4990, 5558 + 32 + 1, Hex.decode("672b402620bbb068a9b673010243de55"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("hkNnBgyXz40de6kX3vkmAWGvatldB-Pp", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86Session1Login() {
        String key = new BruteforceTask(5025, 2469 + 32 + 1, Hex.decode("F5B9DD7D31CF79C069C36657698F000A"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("xjHuxmupDxXebJStnsS2gIixY2c4fgUp", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86ServerSelect() {
        String key = new BruteforceTask(5025,  4097 + 32 + 1, Hex.decode("CBEB0B9002CF91C114688035892F282C"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("E-pksmlfoN6r3_TIBDUPdjpB9gVFZQzu", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream86Game() {
        String key = new BruteforceTask(5025, 8097 + 32 + 1, Hex.decode("89E5B32F75C5648B48615429CAF9D763"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream16Session1Login() {
        String key = new BruteforceTask(0, 10000, Hex.decode("7C816029E153C304714DEFFD9FDFD4A8"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1Login() {
        String key = new BruteforceTask(2393, 666 + 32 + 1, Hex.decode("371D3283A0A09D14BB35CBB004E5F707"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertEquals("Bgun9EAmPhyxYs5z_6B6_ZYOfe1Fzzt4", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1ServerSelect() {
        String key = new BruteforceTask(2393, 1961 + 32 + 1, Hex.decode("0BFABE1B0F3D126D98C923DD0A7C2A2D"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("NvIY0Jci4iVmqs67Kgnm6XcMvYg03wI4", key);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceCamelliaStream3Session1Game() {
        String key = new BruteforceTask(2393, 2309 + 32 + 1, Hex.decode("0189087745144A6E6C39E52B223A19C9"), Hex.decode("2C0000023400000000")).bruteforce().getKey();
        assertEquals("s5-2GHMs5pv_7-K17YW79i8HFKFX9fa6", key);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceCamelliaStream3Session2Login() {
        String key = new BruteforceTask(0, 10000, Hex.decode("793FEF1D1A97001BF829288EB8576D10"), Hex.decode("010000023400000000")).bruteforce().getKey();
        assertNotNull(key);
    }
}