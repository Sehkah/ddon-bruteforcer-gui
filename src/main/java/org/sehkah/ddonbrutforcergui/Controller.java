package org.sehkah.ddonbrutforcergui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.sehkah.ddonbrutforcergui.crypto.Bruteforcer;

public class Controller {
    @FXML
    public TextField expectedHeaderTextField;
    @FXML
    public TextField ciphertextTextField;
    @FXML
    public TextField startMsTextField;
    @FXML
    public TextField stopMsTextField;
    @FXML
    public TextField keyDepthTextField;
    @FXML
    public Button attemptBruteforceButton;
    @FXML
    public TextArea logTextArea;

    @FXML
    public void attemptBruteforceOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            logTextArea.appendText("INFO: Validating input fields...");
            logTextArea.appendText("\n");
            if (!isTextFieldTextValid(expectedHeaderTextField)) {
                return;
            } else {
                if (expectedHeaderTextField.getText().length() < 8) {
                    logTextArea.appendText("ERROR: TextField " + expectedHeaderTextField.getId() + " must be at least of length 8, detected: " + expectedHeaderTextField.getText().length());
                    return;
                }
            }

            if (!isTextFieldTextValid(ciphertextTextField)) {
                return;
            } else {
                if (ciphertextTextField.getText().length() != 32) {
                    logTextArea.appendText("ERROR: TextField " + ciphertextTextField.getId() + " must be of length 32, detected: " + ciphertextTextField.getText().length());
                    return;
                }
            }

            if (!isTextFieldTextValid(startMsTextField)) {
                return;
            } else {
                if (!isTextFieldNumberValid(startMsTextField)) {
                    return;
                }
            }

            if (!isTextFieldTextValid(stopMsTextField)) {
                return;
            } else {
                if (!isTextFieldNumberValid(stopMsTextField)) {
                    return;
                }
            }

            if (!isTextFieldTextValid(keyDepthTextField)) {
                return;
            } else {
                if (!isTextFieldNumberValid(keyDepthTextField)) {
                    return;
                }
            }
            String expectedHeader = expectedHeaderTextField.getText();
            String ciphertext = ciphertextTextField.getText();
            int startMs = Integer.parseInt(startMsTextField.getText());
            int stopMs = Integer.parseInt(stopMsTextField.getText());
            int keyDepth = Integer.parseInt(keyDepthTextField.getText());
            logTextArea.appendText("\n");
            logTextArea.appendText("INFO: Attempting bruteforce with the following parameters:\n");
            logTextArea.appendText("INFO: expectedHeader: " + expectedHeader);
            logTextArea.appendText("\n");
            logTextArea.appendText("INFO: ciphertext: " + ciphertext);
            logTextArea.appendText("\n");
            logTextArea.appendText("INFO: startMs: " + startMs);
            logTextArea.appendText("\n");
            logTextArea.appendText("INFO: keyDepth: " + keyDepth);
            logTextArea.appendText("\n");
            try {
                // TODO multi-thread to avoid lock-ups
                logTextArea.appendText(Bruteforcer.bruteforceRange(startMs, stopMs, keyDepth, ciphertext, expectedHeader));
            } catch (Exception e) {
                e.printStackTrace();
                logTextArea.appendText(e.getLocalizedMessage());
            }
        }
    }

    private boolean isTextFieldTextValid(TextField textField) {
        if (textField.getText().isBlank()) {
            logTextArea.appendText("ERROR: TextField " + textField.getId() + " can not be blank");
            logTextArea.appendText("\n");
            return false;
        }
        return true;
    }

    private boolean isTextFieldNumberValid(TextField textField) {
        if (Long.parseLong(textField.getText()) < 0) {
            logTextArea.appendText("ERROR: TextField " + textField.getId() + " can not be < 0");
            logTextArea.appendText("\n");
            return false;
        }
        return true;
    }
}