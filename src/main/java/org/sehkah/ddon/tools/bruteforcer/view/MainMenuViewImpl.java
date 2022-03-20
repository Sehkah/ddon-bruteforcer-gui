package org.sehkah.ddon.tools.bruteforcer.view;

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
import org.sehkah.ddon.tools.bruteforcer.controller.MainMenuController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.sehkah.ddon.common.view.util.FxmlUtil.loadFxml;

// TODO sanitization and validation logic should be in the model instead
public class MainMenuViewImpl implements MainMenuView {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern hexadecimalPattern = Pattern.compile("0x");
    private static final Pattern whitespacePattern = Pattern.compile("\\s");
    private static final Pattern commaPattern = Pattern.compile(",");

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
            this.stage = loadFxml("bruteforcer_main-menu.fxml", "i18n/bruteforcer_main-menu", this);
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
            String presetsContent = IOUtils.toString(ClassLoader.getSystemClassLoader().getResource("bruteforcer_main-menu-presets.yaml"), StandardCharsets.UTF_8);
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
            String expectedPlaintext = sanitizeByteInput(newValue);
            if (expectedPlaintext.length() < 8 || expectedPlaintext.length() > 32) {
                setIconInvalid(expectedPlaintextIcon);
                expectedPlaintextTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            } else {
                setIconValid(expectedPlaintextIcon);
                expectedPlaintextTextFieldIsValid = true;
                validateBruteforceButton();
            }
        });
        ciphertextTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String ciphertext = sanitizeByteInput(newValue);
            if (ciphertext.length() != 32) {
                setIconInvalid(ciphertextIcon);
                ciphertextTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            } else {
                setIconValid(ciphertextIcon);
                ciphertextTextFieldIsValid = true;
                validateBruteforceButton();
            }
        });
        startMsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long start = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                if (start < 0) {
                    setIconInvalid(startMsIcon);
                    startMsTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    setIconValid(startMsIcon);
                    startMsTextFieldIsValid = true;

                    // Check on relationship with stopMs
                    long stop = Integer.parseInt(whitespacePattern.matcher(stopMsTextField.getText()).replaceAll(""));
                    if (start > stop) {
                        stopMsTextField.textProperty().set(newValue);
                    }
                    validateBruteforceButton();
                }
            } catch (NumberFormatException e) {
                setIconInvalid(startMsIcon);
                startMsTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        stopMsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long stop = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                long start = Integer.parseInt(whitespacePattern.matcher(startMsTextField.getText()).replaceAll(""));
                if (stop < 0 || stop < start) {
                    setIconInvalid(stopMsIcon);
                    stopMsTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    setIconValid(stopMsIcon);
                    stopMsTextFieldIsValid = true;
                    validateBruteforceButton();
                }
            } catch (NumberFormatException e) {
                setIconInvalid(stopMsIcon);
                stopMsTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        keyDepthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                long keyDepth = Integer.parseInt(whitespacePattern.matcher(newValue).replaceAll(""));
                if (keyDepth < 1) {
                    setIconInvalid(keyDepthIcon);
                    keyDepthTextFieldIsValid = false;
                    bruteforceButton.setDisable(true);
                } else {
                    setIconValid(keyDepthIcon);
                    keyDepthTextFieldIsValid = true;
                    validateBruteforceButton();
                }
            } catch (NumberFormatException e) {
                setIconInvalid(keyDepthIcon);
                keyDepthTextFieldIsValid = false;
                bruteforceButton.setDisable(true);
            }
        });
        bruteforceButton.setOnAction(event -> {
            String expectedPlaintext = sanitizeByteInput(expectedPlaintextTextField.getText());
            String ciphertext = sanitizeByteInput(ciphertextTextField.getText());
            int startMs = Integer.parseInt(startMsTextField.getText());
            int stopMs = Integer.parseInt(stopMsTextField.getText());
            int keyDepth = Integer.parseInt(keyDepthTextField.getText());
            bruteforceButton.setDisable(true);
            cancelButton.setDisable(false);
            progressbar.setProgress(0);
            bruteforcedKeyTextField.setText("");
            try {
                logger.debug("request bruteforcing: {} {} {} {} {}", expectedPlaintext, ciphertext, startMs, stopMs, keyDepth);
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

    private void validateBruteforceButton() {
        if (List.of(expectedPlaintextTextFieldIsValid, ciphertextTextFieldIsValid, startMsTextFieldIsValid, stopMsTextFieldIsValid, keyDepthTextFieldIsValid).contains(false)) {
            return;
        }
        bruteforceButton.setDisable(false);
    }

    private void setIconValid(FontIcon icon) {
        icon.setIconLiteral("bi-check-square");
        icon.setIconColor(Color.web("#009900"));
    }

    private void setIconInvalid(FontIcon icon) {
        icon.setIconLiteral("bi-x-square");
        icon.setIconColor(Color.web("#990000"));
    }

    private String sanitizeByteInput(String input) {
        String sanitizedInput = input;
        sanitizedInput = hexadecimalPattern.matcher(sanitizedInput).replaceAll("");
        sanitizedInput = whitespacePattern.matcher(sanitizedInput).replaceAll("");
        sanitizedInput = commaPattern.matcher(sanitizedInput).replaceAll("");
        return sanitizedInput;
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
