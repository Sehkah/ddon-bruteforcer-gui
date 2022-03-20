package org.sehkah.ddon.common.model.crypto;

import java.nio.charset.StandardCharsets;

public class CamelliaConstants {
    public static final byte CAMELLIA_BLOCK_SIZE = 16;
    public static final byte KEY_LENGTH = 32;
    public static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    public static final byte[] CAMELLIA_DEFAULT_KEY = "f23e98HafJdSoaj80QBjhh23oajgklSa".getBytes(StandardCharsets.UTF_8);
    public static final byte[] CAMELLIA_DEFAULT_IV = "$cbM6WP)aX=%J^zA".getBytes(StandardCharsets.UTF_8);
}
