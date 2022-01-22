package org.sehkah.ddonbruteforcergui.controller;

public interface Controller {
    void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);
}
