package org.sehkah.ddonbruteforcergui.view.main;

public class MainMenuPreset {
    private String expectedPlaintext;
    private String ciphertext;
    private String startMs;
    private String stopMs;
    private String keyDepth;

    public String getExpectedPlaintext() {
        return expectedPlaintext;
    }

    public void setExpectedPlaintext(String expectedPlaintext) {
        this.expectedPlaintext = expectedPlaintext;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getStartMs() {
        return startMs;
    }

    public void setStartMs(String startMs) {
        this.startMs = startMs;
    }

    public String getStopMs() {
        return stopMs;
    }

    public void setStopMs(String stopMs) {
        this.stopMs = stopMs;
    }

    public String getKeyDepth() {
        return keyDepth;
    }

    public void setKeyDepth(String keyDepth) {
        this.keyDepth = keyDepth;
    }
}
