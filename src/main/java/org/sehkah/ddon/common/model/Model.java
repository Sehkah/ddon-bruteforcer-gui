package org.sehkah.ddon.common.model;

import org.sehkah.ddon.common.view.View;

public interface Model {
    void close();

    void cancel();

    void addView(View view);

    void removeView(View view);
}
