package org.sehkah.ddonbrutforcergui.prng;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class SeededXorshift128 {
    static {
        NativeLibrary nativeLibrary = NativeLibrary.getInstance("SeededXorshift128.dll");
        Native.register(nativeLibrary);
    }

    private SeededXorshift128() {

    }

    public static native void init(long seed);

    public static native long nextRand();
}
