package org.sehkah.ddon.tools.streamdecryptor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sehkah.ddon.common.view.View;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.SplitStreamDecryptor;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.SplitStreamDecryptorImpl;
import org.sehkah.ddon.tools.streamdecryptor.view.MainMenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainMenuModelImpl implements MainMenuModel {
    private static final Logger logger = LogManager.getLogger();
    private final List<MainMenuView> views;
    private final SplitStreamDecryptor streamdecryptor;
    private ExecutorService executorService;
    private int stopMs;

    public MainMenuModelImpl() {
        streamdecryptor = new SplitStreamDecryptorImpl();
        views = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();
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
        views.forEach(v -> v.queueTaskThreadSafe(() -> v.updateProgress(0)));
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
    public void addView(View view) {
        views.add((MainMenuView) view);
    }

    @Override
    public void removeView(View view) {
        views.add((MainMenuView) view);
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
