package org.sehkah.ddonbruteforcergui.model.crypto;

public interface Bruteforcer {
    void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);

    void cancel();

    void addListener(BruteforceListener listener);

    void removeListener(BruteforceListener listener);

    interface BruteforceListener {
        void onBruteforceStarted(int subsystems);

        void onBruteforceProgressUpdate(int progress);

        void onBruteforceComplete(final BruteforceTaskResult result);

        void onBruteforceError(Exception e);

        void onBruteforceStatusUpdate(String status);
    }
}
