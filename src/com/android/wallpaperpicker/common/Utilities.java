package com.android.wallpaperpicker.common;

import android.os.Build;

public class Utilities {

    public static boolean isAtLeastN() {
        // TODO: replace this with a more final implementation.
        return Build.VERSION.SDK_INT >= 23;
    }
}
