package org.sehkah.ddon.tools.bruteforcer.model.crypto;

public class BruteforceTaskResult {
    private final int milliseconds;
    private final int keyDepth;
    private final String key;

    public BruteforceTaskResult(int milliseconds, int keyDepth, String key) {
        this.milliseconds = milliseconds;
        this.keyDepth = keyDepth;
        this.key = key;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public int getKeyDepth() {
        return keyDepth;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "BruteforceTaskResult{" +
                "milliseconds=" + milliseconds +
                ", keyDepth=" + keyDepth +
                ", key='" + key + '\'' +
                '}';
    }
}
