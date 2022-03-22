package org.sehkah.ddon.tools.streamdecryptor.view;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.sehkah.ddon.common.view.View;

public interface MainMenuView extends View {
    void start(Stage stage);

    Stage getStage();

    default void queueTaskThreadSafe(Runnable r) {
        Platform.runLater(r);
    }
}
