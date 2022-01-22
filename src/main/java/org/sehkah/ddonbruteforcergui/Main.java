package org.sehkah.ddonbruteforcergui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sehkah.ddonbruteforcergui.controller.main.MainMenuController;
import org.sehkah.ddonbruteforcergui.controller.main.MainMenuControllerImpl;

public class Main extends Application {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        logger.debug("Starting application");
        MainMenuController controller = new MainMenuControllerImpl();
        controller.startView(stage);
    }
}