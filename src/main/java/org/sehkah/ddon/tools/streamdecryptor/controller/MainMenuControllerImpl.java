package org.sehkah.ddon.tools.streamdecryptor.controller;

import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sehkah.ddon.tools.streamdecryptor.model.MainMenuModel;
import org.sehkah.ddon.tools.streamdecryptor.model.MainMenuModelImpl;
import org.sehkah.ddon.tools.streamdecryptor.view.MainMenuView;
import org.sehkah.ddon.tools.streamdecryptor.view.MainMenuViewImpl;

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
    public void handleDecryptRequest(String packetStreamInputText) {
        model.decrypt(packetStreamInputText);
    }

    @Override
    public void handleCloseRequest() {
        model.close();
    }
}