package org.sehkah.ddon.tools.streamdecryptor.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.sehkah.ddon.tools.streamdecryptor.controller.MainMenuController;

import java.io.IOException;
import java.util.List;

import static org.sehkah.ddon.common.view.util.FxmlUtil.loadFxml;

// TODO sanitization and validation logic should be in the model instead
public class MainMenuViewImpl implements MainMenuView {
    private static final Logger logger = LogManager.getLogger();

    @FXML
    private TextArea packetStreamInputTextArea;
    @FXML
    private TextArea packetStreamOutputTextArea;
    @FXML
    private Button decryptButton;
    @FXML
    private Button openFileButton;
    @FXML
    private CheckBox hexFormatCheckBox;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button copyButton;
    @FXML
    private FontIcon packetStreamInputKeyFontIcon;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private Label stopwatchLabel;

    private MainMenuController controller;
    private Stage stage;

    public MainMenuViewImpl() {

    }

    public MainMenuViewImpl(MainMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) {
        logger.debug("Starting stage");
        try {
            this.stage = loadFxml("bruteforcer_main-menu.fxml", "i18n/bruteforcer_main-menu", this);
            setupListeners();
            this.stage.centerOnScreen();
            this.stage.setOnCloseRequest(e -> {
                logger.debug("close request");
                controller.handleCloseRequest();
            });
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Started stage");
    }

    private void setupListeners() {
        decryptButton.setOnAction(event -> {
            progressbar.setProgress(0);
            packetStreamOutputTextArea.setText("");
        });
        copyButton.setOnAction(event -> {
            String output = packetStreamOutputTextArea.getText();
            if (!output.isBlank()) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(output);
                clipboard.setContent(content);
            }
        });
    }

    private void setIconValid(FontIcon icon) {
        icon.setIconLiteral("bi-check-square");
        icon.setIconColor(Color.web("#009900"));
    }

    private void setIconInvalid(FontIcon icon) {
        icon.setIconLiteral("bi-x-square");
        icon.setIconColor(Color.web("#990000"));
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setBruteforcedKey(String key) {
        logger.debug("setBruteforcedKey -> {}", key);
        decryptButton.setDisable(false);
    }

    @Override
    public void updateProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
