package org.sehkah.ddon.tools.bruteforcer.model.crypto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BruteforcerTaskTest {
    private static final HexFormat hexFormatter = HexFormat.of();

    /**
     * Works
     */
    @Test
    void testBruteforceStream61Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(4921, 652, hexFormatter.parseHex("F136F3392042F4CF3BF6B9CD6D79DF94"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertEquals("UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream61Session1ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(4921, 2043, hexFormatter.parseHex("EAFA5971990DE6F471DCC66A480B01FF"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("YdT7cIV9UOSxS04T764DHn2PJL-QoJRH", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream61Session1Game() {
        BruteforceTaskResult result = new BruteforceTask(4921, 5000, hexFormatter.parseHex("E54E874FF865F9479FD8B7896B647970"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("q9p2wKk5jOiYuCkqT0nN6giLQa-qTmuz", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream13Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(2344, 237, hexFormatter.parseHex("AF192A98535D0D5CB2DCFC6AFF415104"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertEquals("Vp1QAhucAorAttbmeBc7FiHI3jc6R11q", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream13Session1ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(2344, 1586, hexFormatter.parseHex("3E35163B456B1573DBD6AE25C5513D1D"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("HUOp3tIWjv_2qY53FJY7jC6Hfb_zGcR2", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream13Session1Game() {
        BruteforceTaskResult result = new BruteforceTask(2344, 1924, hexFormatter.parseHex("5A51A55F7DE699972C540D947153C439"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("8cLEnMYDoYzY7rADlaDvCU7m64XOaD-o", result.getKey());
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceStream13Session2Login() {
        BruteforceTaskResult result = new BruteforceTask(2344, 100000000, hexFormatter.parseHex("FDB5BD9CA85262218760B7958F171019"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertNotNull(result);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceStream13Session2ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(2344, 100000000, hexFormatter.parseHex("2273c58f0c47110f95d02f37658fd86c"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertNotNull(result);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceStream13Session3Login() {
        BruteforceTaskResult result = new BruteforceTask(0, 10000, hexFormatter.parseHex("BBBC4B6A40A1CD6BB5C26FD0C66A850F"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertNotNull(result);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream85Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(4990, 3799, hexFormatter.parseHex("06D8D384A2D09B2929ED24156692E2D3"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertEquals("J2g4pE2_heyqIAengWy0N6D1SEklxz8I", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream85Session1ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(4990, 5204, hexFormatter.parseHex("179522096DFDA401FA4183A4C6B6058B"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("N5ee3Ra0KBZ-UVELDmKRnhe8wqWvWEm7", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream85Session1Game() {
        BruteforceTaskResult result = new BruteforceTask(4990, 5558, hexFormatter.parseHex("672B402620BBB068A9B673010243DE55"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("hkNnBgyXz40de6kX3vkmAWGvatldB-Pp", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream86Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(5025, 2469, hexFormatter.parseHex("F5B9DD7D31CF79C069C36657698F000A"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertEquals("xjHuxmupDxXebJStnsS2gIixY2c4fgUp", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream86ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(5025, 4097, hexFormatter.parseHex("CBEB0B9002CF91C114688035892F282C"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("E-pksmlfoN6r3_TIBDUPdjpB9gVFZQzu", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream86Game() {
        BruteforceTaskResult result = new BruteforceTask(5025, 8097, hexFormatter.parseHex("89E5B32F75C5648B48615429CAF9D763"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertNotNull(result);
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceStream16Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(0, 10000, hexFormatter.parseHex("7C816029E153C304714DEFFD9FDFD4A8"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertNotNull(result);
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream3Session1Login() {
        BruteforceTaskResult result = new BruteforceTask(2393, 666, hexFormatter.parseHex("371D3283A0A09D14BB35CBB004E5F707"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertEquals("Bgun9EAmPhyxYs5z_6B6_ZYOfe1Fzzt4", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream3Session1ServerSelect() {
        BruteforceTaskResult result = new BruteforceTask(2393, 1961, hexFormatter.parseHex("0BFABE1B0F3D126D98C923DD0A7C2A2D"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("NvIY0Jci4iVmqs67Kgnm6XcMvYg03wI4", result.getKey());
    }

    /**
     * Works
     */
    @Test
    void testBruteforceStream3Session1Game() {
        BruteforceTaskResult result = new BruteforceTask(2393, 2309, hexFormatter.parseHex("0189087745144A6E6C39E52B223A19C9"), hexFormatter.parseHex("2C0000023400000000")).bruteforce();
        assertEquals("s5-2GHMs5pv_7-K17YW79i8HFKFX9fa6", result.getKey());
    }

    /**
     * TODO: not cracked
     */
    @Test
    @Disabled
    void testBruteforceStream3Session2Login() {
        BruteforceTaskResult result = new BruteforceTask(0, 10000, hexFormatter.parseHex("793FEF1D1A97001BF829288EB8576D10"), hexFormatter.parseHex("010000023400000000")).bruteforce();
        assertNotNull(result);
    }
}