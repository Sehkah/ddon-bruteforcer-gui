package org.sehkah.ddon.tools.bruteforcer.controller;

import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sehkah.ddon.tools.bruteforcer.model.MainMenuModel;
import org.sehkah.ddon.tools.bruteforcer.model.MainMenuModelImpl;
import org.sehkah.ddon.tools.bruteforcer.view.MainMenuView;
import org.sehkah.ddon.tools.bruteforcer.view.MainMenuViewImpl;

public class MainMenuControllerImpl implements MainMenuController {
    private static final Logger logger = LogManager.getLogger();

    private final MainMenuModel model;
    private final MainMenuView view;

    public MainMenuControllerImpl() {
        model = new MainMenuModelImpl();
        view = new MainMenuViewImpl(this);
        model.addView(view);
    }

    @Override
    public void startView(Stage stage) {
        view.start(stage);
    }

    @Override
    public void handleBruteforceRequest(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext) {
        model.bruteforce(startMs, stopMs, keyDepth, ciphertext, expectedPlaintext);
    }

    @Override
    public void handleCloseRequest() {
        model.close();
    }

    @Override
    public void handleCancelRequest() {
        model.cancel();
    }
}