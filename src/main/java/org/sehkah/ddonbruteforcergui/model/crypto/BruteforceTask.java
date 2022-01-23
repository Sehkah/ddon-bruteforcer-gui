package org.sehkah.ddonbruteforcergui.model.crypto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.sehkah.ddonbruteforcergui.model.crypto.CamelliaConstants.*;

public class BruteforceTask implements Callable<BruteforceTaskResult> {
    private static final Logger logger = LogManager.getLogger();
    private static final CamelliaEngine engine = new CamelliaEngine();

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 0);
    }

    private final int milliseconds;
    private final int keyDepth;
    private final byte[] ciphertext;
    private final byte[] expectedPlaintext;
    private Bruteforcer.BruteforceTaskListener listener;

    public BruteforceTask(int milliseconds, int keyDepth, byte[] ciphertext, byte[] expectedPlaintext) {
        this.milliseconds = milliseconds;
        this.keyDepth = keyDepth;
        this.ciphertext = ciphertext;
        this.expectedPlaintext = expectedPlaintext;
    }

    public BruteforceTask(int milliseconds, int keyDepth, byte[] ciphertext, byte[] expectedPlaintext, Bruteforcer.BruteforceTaskListener listener) {
        this.milliseconds = milliseconds;
        this.keyDepth = keyDepth;
        this.ciphertext = ciphertext;
        this.expectedPlaintext = expectedPlaintext;
        this.listener = listener;
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
        BruteforceTaskResult result = bruteforce();
        if (listener != null) {
            listener.onBruteforceTaskComplete(result);
        }
        logger.trace("finished checking {}ms", milliseconds);
        return result;
    }

    public BruteforceTaskResult bruteforce() {
        SeededXorshift128.init(milliseconds);

        char[] keyBuffer = new char[keyDepth + KEY_LENGTH];
        // Initialize PRNG with the current ms time, then generate the full potential key buffer.
        for (int i = 0; i < keyDepth + KEY_LENGTH; i++) {
            keyBuffer[i] = ALPHABET[(int) (SeededXorshift128.nextRand() & 63)];
        }

        byte[] plaintext = new byte[16];
        KeyParameter keyParameter;
        // Go over the key buffer and try every index as the starting position of the key.
        for (int i = 0; i <= keyDepth; i++) {
            char[] key = Arrays.copyOfRange(keyBuffer, i, i + KEY_LENGTH);
            keyParameter = new KeyParameter(charToByte(key));
            engine.init(false, keyParameter);
            engine.processBlock(ciphertext, 0, plaintext, 0);

            // XOR output with the provided IV.
            for (int j = 0; j < CAMELLIA_BLOCK_SIZE; j++) {
                plaintext[j] ^= CAMELLIA_DEFAULT_IV[j];
            }

            // Check if the current key index decrypts to the expected LoginServer->Client packet.
            if (Arrays.equals(expectedPlaintext, Arrays.copyOfRange(plaintext, 0, expectedPlaintext.length))) {
                BruteforceTaskResult result = new BruteforceTaskResult(milliseconds, i, new String(key));
                logger.info(result);
                return result;
            }
        }
        return null;
    }
}
