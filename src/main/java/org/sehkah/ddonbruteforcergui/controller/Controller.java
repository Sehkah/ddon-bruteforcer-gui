package org.sehkah.ddonbruteforcergui.controller;

public interface Controller {
    void handleBruteforceRequest(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);

    void handleCloseRequest();

    void handleCancelRequest();
}
