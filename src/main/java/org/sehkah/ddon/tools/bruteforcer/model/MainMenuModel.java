package org.sehkah.ddon.tools.bruteforcer.model;

import org.sehkah.ddon.common.model.Model;

public interface MainMenuModel extends Model {
    void bruteforce(int startMs, int stopMs, int keyDepth, String ciphertext, String expectedPlaintext);
}
