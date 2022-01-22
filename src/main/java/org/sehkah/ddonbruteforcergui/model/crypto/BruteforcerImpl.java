package org.sehkah.ddonbruteforcergui.model.crypto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BruteforcerImpl implements Bruteforcer {
    private static final Logger logger = LogManager.getLogger();
    private final ExecutorService executor;
    private final List<Bruteforcer.BruteforceListener> bruteforceListeners;
    private BruteforceTask bruteforceTask;

    public BruteforcerImpl() {
        executor = Executors.newSingleThreadExecutor();
        bruteforceListeners = new LinkedList<>();
    }

    @Override
    public void cancel() {
        if (bruteforceTask != null) {
            executor.shutdownNow();
        }
    }

    @Override
    public void addListener(final Bruteforcer.BruteforceListener listener) {
        bruteforceListeners.add(listener);
    }

    @Override
    public void removeListener(final Bruteforcer.BruteforceListener listener) {
        bruteforceListeners.remove(listener);
    }

    @Override
    public void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        if (bruteforceTask != null) {
            bruteforceTask = null;
        }
        Instant start = Instant.now();
        BruteforceTaskResult result = null;
        for (int milliseconds = startMs; milliseconds <= stopMs; milliseconds++) {
            bruteforceTask = new BruteforceTask(milliseconds, keyDepth, Hex.decode(ciphertext), Hex.decode(expectedPlaintext));
            try {
                result = (BruteforceTaskResult) executor.submit(bruteforceTask).get();
                if (result != null) {
                    break;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        Instant finish = Instant.now();
        final String timeElapsed = Duration.between(start, finish).toString();
        BruteforceTaskResult finalResult = result;
        bruteforceListeners.forEach(bruteforceListener -> bruteforceListener.onBruteforceComplete(finalResult));
    }
}
