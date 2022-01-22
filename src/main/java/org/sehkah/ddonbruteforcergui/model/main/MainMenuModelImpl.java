package org.sehkah.ddonbruteforcergui.model.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sehkah.ddonbruteforcergui.model.crypto.BruteforceTaskResult;
import org.sehkah.ddonbruteforcergui.model.crypto.Bruteforcer;
import org.sehkah.ddonbruteforcergui.model.crypto.BruteforcerImpl;
import org.sehkah.ddonbruteforcergui.view.View;
import org.sehkah.ddonbruteforcergui.view.main.MainMenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainMenuModelImpl implements MainMenuModel, Bruteforcer.BruteforceListener {
    private static final Logger logger = LogManager.getLogger();
    private final List<MainMenuView> views;
    private final Bruteforcer bruteforcer;
    private ExecutorService executorService;
    private int stopMs;

    public MainMenuModelImpl() {
        bruteforcer = new BruteforcerImpl();
        bruteforcer.addListener(this);
        views = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        this.stopMs = stopMs;
        logger.debug("MainMenuModelImpl -> bruteforce");
        executorService.submit(() -> bruteforcer.start(startMs, stopMs, keyDepth, ciphertext, expectedPlaintext));
    }

    @Override
    public void cancel() {
        logger.debug("cancel requested");
        bruteforcer.cancel();
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
        bruteforcer.close();
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addView(View view) {
        views.add((MainMenuView) view);
    }

    @Override
    public void removeView(View view) {
        views.add((MainMenuView) view);
    }

    @Override
    public void onBruteforceProgressUpdate(int progress) {
        double scaledProgress = scaleTo1(0, stopMs, progress);
        views.forEach(v -> v.queueTaskThreadSafe(() -> v.updateProgress(scaledProgress)));
    }

    @Override
    public void onBruteforceComplete(BruteforceTaskResult result) {
        views.forEach(v -> v.queueTaskThreadSafe(() -> v.setBruteforcedKey(result.getKey())));
    }

    private double scaleTo1(double oldMin, double oldMax, double value) {
        return scaleTo(oldMin, oldMax, 0, 1, value);
    }

    private double scaleTo(double oldMin, double oldMax, double newMin, double newMax, double value) {
        if (oldMin != oldMax && newMin != newMax) {
            return (((value - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
        } else {
            return (newMax + newMin) / 2;
        }
    }
}
