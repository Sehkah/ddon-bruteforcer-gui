package org.sehkah.ddonbruteforcergui.model.crypto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class BruteforcerImpl implements Bruteforcer, Bruteforcer.BruteforceTaskListener {
    private static final Logger logger = LogManager.getLogger();
    private final List<Bruteforcer.BruteforceListener> bruteforceListeners;
    private ExecutorService executorService;
    private int progress;

    public BruteforcerImpl() {
        executorService = Executors.newSingleThreadExecutor(); // TODO: Replace with workStealingPool once DLL is rewritten
        bruteforceListeners = new LinkedList<>();
    }

    @Override
    public void cancel() {
        logger.debug("cancel requested");
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (executorService.isShutdown()) {
            logger.debug("executor is shut down");
            executorService = Executors.newSingleThreadExecutor();
        }
    }

    @Override
    public void close() {
        logger.debug("close requested");
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
    public void start(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        logger.debug("bruteforce requested: {} {} {} {} {}", expectedPlaintext, ciphertext, startMs, stopMs, keyDepth);
        bruteforceListeners.forEach(bruteforceListener -> bruteforceListener.onBruteforceProgressUpdate(0));
        byte[] byteCiphertext = Hex.decode(ciphertext);
        byte[] byteExpectedPlaintext = Hex.decode(expectedPlaintext);
        List<BruteforceTask> bruteforceTasks = new ArrayList<>(stopMs - startMs);
        for (int milliseconds = startMs; milliseconds <= stopMs; milliseconds++) {
            bruteforceTasks.add(new BruteforceTask(milliseconds, keyDepth, byteCiphertext, byteExpectedPlaintext, this));
        }
        try {
            bruteforceAll(executorService, bruteforceTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void bruteforceAll(Executor executor, Collection<BruteforceTask> tasks) throws InterruptedException {
        CompletionService<BruteforceTaskResult> cs = new ExecutorCompletionService<>(executor);
        int n = tasks.size();
        List<Future<BruteforceTaskResult>> futures = new ArrayList<>(n);
        BruteforceTaskResult result = null;
        try {
            tasks.forEach(solver -> futures.add(cs.submit(solver)));
            for (int i = n; i > 0; i--) {
                try {
                    BruteforceTaskResult r = cs.take().get();
                    if (r != null) {
                        result = r;
                        break;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            futures.forEach(future -> future.cancel(true));
        }

        BruteforceTaskResult finalResult;
        if (result == null || result.getKey() == null || result.getKey().isBlank()) {
            finalResult = new BruteforceTaskResult(-1, -1, "Key not found");
        } else {
            finalResult = result;
        }

        bruteforceListeners.forEach(bruteforceListener -> bruteforceListener.onBruteforceComplete(finalResult));
    }

    @Override
    public void onBruteforceTaskComplete(BruteforceTaskResult result) {
        bruteforceListeners.forEach(bruteforceListener -> bruteforceListener.onBruteforceProgressUpdate(++progress));
    }
}
