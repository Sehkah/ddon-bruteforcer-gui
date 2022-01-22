package org.sehkah.ddonbruteforcergui.model.main;

import org.sehkah.ddonbruteforcergui.model.crypto.BruteforceTaskResult;
import org.sehkah.ddonbruteforcergui.model.crypto.Bruteforcer;
import org.sehkah.ddonbruteforcergui.model.crypto.BruteforcerImpl;
import org.sehkah.ddonbruteforcergui.view.View;
import org.sehkah.ddonbruteforcergui.view.main.MainMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuModelImpl implements MainMenuModel, Bruteforcer.BruteforceListener {
    private final List<MainMenuView> views;
    private final Bruteforcer bruteforcer;

    public MainMenuModelImpl() {
        bruteforcer = new BruteforcerImpl();
        views = new ArrayList<>();
    }

    @Override
    public void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        bruteforcer.bruteforce(startMs, stopMs, keyDepth, ciphertext, expectedPlaintext);
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
    public void onBruteforceStarted(int subsystems) {

    }

    @Override
    public void onBruteforceProgressUpdate(int progress) {

    }

    @Override
    public void onBruteforceComplete(BruteforceTaskResult result) {
        views.forEach(v -> v.setBruteforcedKey(result.getKey()));
    }

    @Override
    public void onBruteforceError(Exception e) {

    }

    @Override
    public void onBruteforceStatusUpdate(String status) {

    }
}
