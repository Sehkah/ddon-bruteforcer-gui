package org.sehkah.ddon.common.model.crypto;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CamelliaDecryptorTest {
    /**
     * Works Server #000000 (1.0.2) L2C_CLIENT_CHALLENGE_RES
     */
    @Test
    void decryptStream61Packet3() {
        String packetData = "AGDxNvM5IEL0zzv2uc1ted+UYDHeP6hAEv5WOrQKk2EtLT2oCpLHLZ3GQIVVOFiOPg34TtRWwB0JNMK9VGkjXdeeMdyeNBBFkd6gsuVbn4YQFiy60lDxznttS1lIVYRDBlU=";
        String key = "UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-";
        byte[] decryptedPacketData = CamelliaDecryptor.decryptPacketData(Base64.decode(packetData), key.getBytes(StandardCharsets.UTF_8));
        assertEquals("010000023400000000000000000000000010108979EED698AD50785502CCB988AEC98847C540EFE6B8B1739F39A9725FD339D78A190000000028FE7C000000000070EC1B050000000022B9763F010000002A65404AB1FD7C26C0669FEA530126",
                Hex.toHexString(decryptedPacketData).toUpperCase());
    }

    /**
     * Works Client #000001 (0.1.1) C2L_LOGIN_REQ
     */
    @Test
    void decryptStream61Packet4() {
        String packetData = "ACCEtsCJbQvpj9CxWhGahqFyNNrsOlEOmueP578r4UC1bg==";
        String key = "UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-";
        byte[] decryptedPacketData = CamelliaDecryptor.decryptPacketData(Base64.decode(packetData), key.getBytes(StandardCharsets.UTF_8));
        assertEquals("0000010100000000010014386A667671716C75796130346B34386B3430346301",
                Hex.toHexString(decryptedPacketData).toUpperCase());
    }
}