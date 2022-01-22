package org.sehkah.ddonbruteforcergui.model.crypto;

import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class BruteforceTask implements Callable<BruteforceTaskResult> {
    private static final byte CAMELLIA_BLOCK_SIZE = 16;
    private static final byte KEY_LENGTH = 32;
    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final byte[] camellia_default_iv = "$cbM6WP)aX=%J^zA".getBytes(StandardCharsets.UTF_8);
    private static final CamelliaEngine engine = new CamelliaEngine();

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final int milliseconds;
    private final int keyDepth;
    private final byte[] ciphertext;
    private final byte[] expectedPlaintext;

    public BruteforceTask(int milliseconds, int keyDepth, byte[] ciphertext, byte[] expectedPlaintext) {
        this.milliseconds = milliseconds;
        this.keyDepth = keyDepth;
        this.ciphertext = ciphertext;
        this.expectedPlaintext = expectedPlaintext;
    }

    private static byte[] charToByte(char[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            // This is fine since the alphabet is just ASCII text
            result[i] = (byte) array[i];
        }
        return result;
    }

    @Override
    public BruteforceTaskResult call() {
        return bruteforce();
    }

    public BruteforceTaskResult bruteforce() {
        SeededXorshift128.init(milliseconds);

        char[] keyBuffer = new char[keyDepth];
        // Initialize PRNG with the current ms time, then generate the full potential key buffer.
        for (int i = 0; i < keyDepth; i++) {
            keyBuffer[i] = alphabet[(int) (SeededXorshift128.nextRand() & 63)];
        }

        byte[] plaintext = new byte[16];
        KeyParameter keyParameter;
        // Go over the key buffer and try every index as the starting position of the key.
        int maxDepth = keyDepth - KEY_LENGTH;
        for (int i = 0; i < maxDepth; i++) {
            char[] key = Arrays.copyOfRange(keyBuffer, i, i + KEY_LENGTH);
            keyParameter = new KeyParameter(charToByte(key));
            engine.init(false, keyParameter);
            engine.processBlock(ciphertext, 0, plaintext, 0);

            // XOR output with the provided IV.
            for (int j = 0; j < CAMELLIA_BLOCK_SIZE; j++) {
                plaintext[j] ^= camellia_default_iv[j];
            }

            // Check if the current key index decrypts to the expected LoginServer->Client packet.
            if (Arrays.equals(expectedPlaintext, Arrays.copyOfRange(plaintext, 0, expectedPlaintext.length))) {
                BruteforceTaskResult result = new BruteforceTaskResult(milliseconds, i, new String(key));
                System.out.println(result);
                return result;
            }
        }
        return null;
    }
}
