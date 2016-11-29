package com.android.wallpaperpicker.common;

import android.content.Context;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

public class WallpaperManagerCompatVN extends WallpaperManagerCompatV16 {
    public WallpaperManagerCompatVN(Context context) {
        super(context);
    }

    @Override
    public void setStream(final InputStream data, Rect visibleCropHint, boolean allowBackup,
            int which) throws IOException {
        //TODO: mWallpaperManager.setStream(data, visibleCropHint, allowBackup, which);
        mWallpaperManager.setStream(data);
    }
}
