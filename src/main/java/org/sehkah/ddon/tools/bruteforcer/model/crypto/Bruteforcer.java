package org.sehkah.ddon.tools.bruteforcer.model.crypto;

public interface Bruteforcer {
    void start(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);

    void cancel();

    void close();

    void addListener(BruteforceListener listener);

    void removeListener(BruteforceListener listener);

    interface BruteforceListener {
        void onBruteforceProgressUpdate(int progress);

        void onBruteforceComplete(final BruteforceTaskResult result);
    }

    interface BruteforceTaskListener {
        void onBruteforceTaskComplete(final BruteforceTaskResult result);
    }
}
