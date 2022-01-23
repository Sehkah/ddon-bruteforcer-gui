package org.sehkah.ddonbruteforcergui.view.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.sehkah.ddonbruteforcergui.controller.main.MainMenuController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.sehkah.ddonbruteforcergui.view.util.FxmlUtil.loadFxml;

public class MainMenuViewImpl implements MainMenuView {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern whitespacePattern = Pattern.compile("\\s");

    @FXML
    private ComboBox<String> bruteforcePresetsComboBox;
    @FXML
    private TextField expectedPlaintextTextField;
    @FXML
    private TextField ciphertextTextField;
    @FXML
    private TextField startMsTextField;
    @FXML
    private TextField stopMsTextField;
    @FXML
    private TextField keyDepthTextField;
    @FXML
    private TextField bruteforcedKeyTextField;
    @FXML
    private Button bruteforceButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button copyButton;
    @FXML
    private FontIcon expectedPlaintextIcon;
    @FXML
    private FontIcon ciphertextIcon;
    @FXML
    private FontIcon startMsIcon;
    @FXML
    private FontIcon stopMsIcon;
    @FXML
    private FontIcon keyDepthIcon;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private Label stopwatchLabel;

    private boolean expectedPlaintextTextFieldIsValid = false;
    private boolean ciphertextTextFieldIsValid = false;
    private boolean startMsTextFieldIsValid = true;
    private boolean stopMsTextFieldIsValid = true;
    private boolean keyDepthTextFieldIsValid = true;
    private Map<String, MainMenuPreset> presets;
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
            this.stage = loadFxml("main-menu.fxml", "i18n/main-menu", this);
            setupListeners();
            setupData();
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

    private void setupData() {
        logger.debug("Attempting to load main menu presets");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            String presetsContent = IOUtils.toString(ClassLoader.getSystemClassLoader().getResource("main-menu-presets.yaml"), StandardCharsets.UTF_8);
            presets = mapper.readValue(presetsContent, new TypeReference<>() {
            });
            presets.keySet().forEach(preset -> bruteforcePresetsComboBox.getItems().add(preset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupListeners() {
        bruteforcePresetsComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            MainMenuPreset preset = presets.get(newValue);
            expectedPlaintextTextField.setText(preset.getExpectedPlaintext());
            ciphertextTextField.setText(preset.getCiphertext());
            startMsTextField.setText(preset.getStartMs());
            stopMsTextField.setText(preset.getStopMs());
            keyDepthTextField.setText(preset.getKeyDepth());
        });
        expectedPlaintextTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String expectedPlaintext = whitespacePattern.matcher(newValue).replaceAll("");
            if (expectedPlaintext.length() < 8 || expectedPlaintext.length() > 32) {
                expectedPlaintextIcon.setIconLiteral("bi-x-square");
                expectedPlaintextIcon.setIconColor(Color.web("#990000"));
                expectedPlaintextTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            } else {
                expectedPlaintextIcon.setIconLiteral("bi-check-square");
                expectedPlaintextIcon.setIconColor(Color.web("#009900"));
                expectedPlaintextTextFieldIsValid = true;
                if (!List.of(ciphertextTextFieldIsValid, startMsTextFieldIsValid, stopMsTextFieldIsValid, keyDepthTextFieldIsValid).contains(false)) {
                    bruteforceButton.setDisable(false);
                }
            }
        });
        ciphertextTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String ciphertext = whitespacePattern.matcher(newValue).replaceAll("");
            if (ciphertext.length() != 32) {
                ciphertextIcon.setIconLiteral("bi-x-square");
                ciphertextIcon.setIconColor(Color.web("#990000"));
                ciphertextTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            } else {
                ciphertextIcon.setIconLiteral("bi-check-square");
                ciphertextIcon.setIconColor(Color.web("#009900"));
                ciphertextTextFieldIsValid = true;
                if (!List.of(expectedPlaintextTextFieldIsValid, startMsTextFieldIsValid, stopMsTextFieldIsValid, keyDepthTextFieldIsValid).contains(false)) {
                    bruteforceButton.setDisable(false);
                }
            }
        });
        startMsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long start = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                if (start < 0) {
                    startMsIcon.setIconLiteral("bi-x-square");
                    startMsIcon.setIconColor(Color.web("#990000"));
                    startMsTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    startMsIcon.setIconLiteral("bi-check-square");
                    startMsIcon.setIconColor(Color.web("#009900"));
                    startMsTextFieldIsValid = true;
                    long stop = Integer.parseInt(whitespacePattern.matcher(stopMsTextField.getText()).replaceAll(""));
                    if (start > stop) {
                        stopMsTextField.textProperty().set(newValue);
                        stopMsTextFieldIsValid = true;
                    } else {
                        stopMsTextField.getStyleClass().set(1, null);
                        stopMsTextFieldIsValid = true;
                    }
                    if (!List.of(expectedPlaintextTextFieldIsValid, ciphertextTextFieldIsValid, keyDepthTextFieldIsValid).contains(false)) {
                        bruteforceButton.setDisable(false);
                    }
                }
            } catch (NumberFormatException e) {
                startMsIcon.setIconLiteral("bi-x-square");
                startMsIcon.setIconColor(Color.web("#990000"));
                startMsTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        stopMsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long stop = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                long start = Integer.parseInt(whitespacePattern.matcher(startMsTextField.getText()).replaceAll(""));
                if (stop < 0 || stop < start) {
                    stopMsIcon.setIconLiteral("bi-x-square");
                    stopMsIcon.setIconColor(Color.web("#990000"));
                    stopMsTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    stopMsIcon.setIconLiteral("bi-check-square");
                    stopMsIcon.setIconColor(Color.web("#009900"));
                    stopMsTextFieldIsValid = true;
                    if (!List.of(expectedPlaintextTextFieldIsValid, ciphertextTextFieldIsValid, startMsTextFieldIsValid, keyDepthTextFieldIsValid).contains(false)) {
                        bruteforceButton.setDisable(false);
                    }
                }
            } catch (NumberFormatException e) {
                stopMsIcon.setIconLiteral("bi-x-square");
                stopMsIcon.setIconColor(Color.web("#990000"));
                stopMsTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        keyDepthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long keyDepth = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                if (keyDepth < 1) {
                    keyDepthIcon.setIconLiteral("bi-x-square");
                    keyDepthIcon.setIconColor(Color.web("#990000"));
                    keyDepthTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    keyDepthIcon.setIconLiteral("bi-check-square");
                    keyDepthIcon.setIconColor(Color.web("#009900"));
                    keyDepthTextFieldIsValid = true;
                    if (!List.of(expectedPlaintextTextFieldIsValid, ciphertextTextFieldIsValid, startMsTextFieldIsValid, stopMsTextFieldIsValid).contains(false)) {
                        bruteforceButton.setDisable(false);
                    }
                }
            } catch (NumberFormatException e) {
                keyDepthIcon.setIconLiteral("bi-check-square");
                keyDepthIcon.setIconColor(Color.web("#009900"));
                keyDepthTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        bruteforceButton.setOnAction(event -> {
            String expectedPlaintext = expectedPlaintextTextField.getText();
            String ciphertext = ciphertextTextField.getText();
            int startMs = Integer.parseInt(startMsTextField.getText());
            int stopMs = Integer.parseInt(stopMsTextField.getText());
            int keyDepth = Integer.parseInt(keyDepthTextField.getText());
            bruteforceButton.setDisable(true);
            cancelButton.setDisable(false);
            progressbar.setProgress(0);
            try {
                logger.debug("triggered bruteforcing: {} {} {} {} {}", expectedPlaintext, ciphertext, startMs, stopMs, keyDepth);
                controller.handleBruteforceRequest(startMs, stopMs, keyDepth, ciphertext, expectedPlaintext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        copyButton.setOnAction(event -> {
            String key = bruteforcedKeyTextField.getText();
            if (!key.isBlank()) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(bruteforcedKeyTextField.getText());
                clipboard.setContent(content);
            }
        });
        cancelButton.setOnAction(event -> {
            bruteforceButton.setDisable(false);
            cancelButton.setDisable(true);
            controller.handleCancelRequest();
        });
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setBruteforcedKey(String key) {
        logger.debug("setBruteforcedKey -> {}", key);
        bruteforcedKeyTextField.setText(key);
        bruteforceButton.setDisable(false);
        cancelButton.setDisable(true);
    }

    @Override
    public void updateProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
