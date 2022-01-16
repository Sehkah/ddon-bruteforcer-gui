package org.sehkah.ddonbrutforcergui.crypto;

import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.sehkah.ddonbrutforcergui.prng.SeededXorshift128;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;

public class Bruteforcer {
    private static final byte CAMELLIA_BLOCK_SIZE = 16;
    private static final byte KEY_LENGTH = 32;
    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final byte[] camellia_default_iv = "$cbM6WP)aX=%J^zA".getBytes(StandardCharsets.UTF_8);
    private static final CamelliaEngine engine = new CamelliaEngine();

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private Bruteforcer() {

    }

    public static String bruteforceRange(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        return bruteforceRange(startMs, stopMs, keyDepth, Hex.decode(ciphertext), Hex.decode(expectedPlaintext));
    }

    // TODO parallelize
    public static String bruteforceRange(int startMs, int stopMs, int keyDepth, byte[] ciphertext, byte[] expectedPlaintext) {
        String key = null;
        for (int i = startMs; i < stopMs; i++) {
            key = bruteforceCamellia(i, keyDepth, ciphertext, expectedPlaintext);
            if (key != null) {
                break;
            }
        }
        return key;
    }

    public static String bruteforceCamellia(int milliseconds, int keyDepth, byte[] ciphertext, byte[] expectedPlaintext) {
        SeededXorshift128.init(milliseconds);

        char[] keyBuffer = new char[keyDepth];
        // Initialize PRNG with the current ms time, then generate the full potential key buffer.
        for (int i = 0; i < keyDepth; i++) {
            keyBuffer[i] = alphabet[(int) (SeededXorshift128.nextRand() & 63)];
        }

        byte[] plaintext = new byte[16];
        KeyParameter keyParameter;
        // Go over the key buffer and try every index as the starting position of the key.
        for (int i = 0; i < keyDepth - KEY_LENGTH; i++) {
            char[] key = Arrays.copyOfRange(keyBuffer, i, i + KEY_LENGTH);
            keyParameter = new KeyParameter(charToByte(key));
            engine.init(false, keyParameter);
            engine.processBlock(ciphertext, 0, plaintext, 0);

            // XOR output with the provided IV.
            for (int j = 0; j < CAMELLIA_BLOCK_SIZE; j++) {
                plaintext[j] ^= camellia_default_iv[j];
            }

            // Check if the current key index decrypts to the expected LoginServer->Client packet.
            if (
                    plaintext[0] == expectedPlaintext[0] && // Group IDX
                            plaintext[1] == expectedPlaintext[1] && // Handler ID lo
                            plaintext[2] == expectedPlaintext[2] && // Handler ID hi
                            plaintext[3] == expectedPlaintext[3] && // Handler sub id
                            plaintext[4] == expectedPlaintext[4] && // Unk field, 0x34 when comes from server? unverified

                            // Packet counter bytes. Always seems to be 0 when coming from the server, which this packet is.
                            // This needs to be verified.
                            plaintext[5] == expectedPlaintext[5] &&
                            plaintext[6] == expectedPlaintext[6] &&
                            plaintext[7] == expectedPlaintext[7] &&
                            plaintext[8] == expectedPlaintext[8]) {

                String keyOut = new String(key);
                // TODO, connect to GUI
                System.out.println("Found match at ms" + milliseconds + ", i:" + i + ", key: " + keyOut + "\n");
                return keyOut;
            }
        }
        return null;
    }

    private static byte[] charToByte(char[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            // This is fine since the alphabet is just ASCII text
            result[i] = (byte) array[i];
        }
        return result;
    }
}
