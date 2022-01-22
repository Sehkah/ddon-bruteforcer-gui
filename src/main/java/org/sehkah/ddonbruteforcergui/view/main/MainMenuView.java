package org.sehkah.ddonbruteforcergui.view.main;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.sehkah.ddonbruteforcergui.view.View;

public interface MainMenuView extends View {
    void start(Stage stage);

    Stage getStage();

    default void queueTaskThreadSafe(Runnable r) {
        Platform.runLater(r);
    }
}
