package org.sehkah.ddonbruteforcergui.model.crypto;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Arrays;

import static org.sehkah.ddonbruteforcergui.model.crypto.CamelliaConstants.CAMELLIA_DEFAULT_IV;
import static org.sehkah.ddonbruteforcergui.model.crypto.CamelliaConstants.CAMELLIA_DEFAULT_KEY;

public class CamelliaDecryptor {
    private static final PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new CamelliaEngine()), new ZeroBytePadding());

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 0);
    }

    private CamelliaDecryptor() {

    }

    public static byte[] decryptPacketKeyExchangeData(byte[] input) {
        return decryptPacketData(input, CAMELLIA_DEFAULT_KEY);
    }

    public static byte[] decryptPacketData(byte[] input, byte[] key) {
        byte[] inputBytes = Arrays.copyOfRange(input, 2, input.length);
        byte[] outputBytes = new byte[inputBytes.length];
        try {
            KeyParameter keyParam = new KeyParameter(Arrays.copyOf(key, 32));
            ParametersWithIV keyParamWithIv = new ParametersWithIV(keyParam, CAMELLIA_DEFAULT_IV);
            cipher.init(false, keyParamWithIv);
            int length = cipher.processBytes(inputBytes, 0, inputBytes.length, outputBytes, 0);
            cipher.doFinal(outputBytes, length);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return outputBytes;
    }
}
