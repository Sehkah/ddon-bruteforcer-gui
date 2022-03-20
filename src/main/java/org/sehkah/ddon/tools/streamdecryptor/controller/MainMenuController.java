package org.sehkah.ddon.tools.streamdecryptor.controller;

import javafx.stage.Stage;
import org.sehkah.ddon.common.controller.Controller;

public interface MainMenuController extends Controller {
    void startView(Stage stage);

    void handleDecryptRequest(String packetStreamInputText);
}
