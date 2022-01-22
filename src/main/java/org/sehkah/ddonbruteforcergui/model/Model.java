package org.sehkah.ddonbruteforcergui.model;

import org.sehkah.ddonbruteforcergui.view.View;

public interface Model {
    void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);

    void close();

    void cancel();

    void addView(View view);

    void removeView(View view);
}
