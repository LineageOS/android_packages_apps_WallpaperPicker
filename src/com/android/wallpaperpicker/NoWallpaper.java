/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.wallpaperpicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import java.io.IOException;

public class NoWallpaper extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.wallpaper_preview);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
    }

    public void setWallpaper(View v) {
        WallpaperManager wm = WallpaperManager.getInstance(this);
        new AlertDialog.Builder(this)
            .setTitle(R.string.wallpaper_instructions)
            .setAdapter(new WallpaperListBaseAdapter(this), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedItemIndex) {
                int whichWallpaper;
                if (selectedItemIndex == 0) {
                    whichWallpaper = WallpaperManager.FLAG_SYSTEM;
                } else if (selectedItemIndex == 1) {
                    whichWallpaper = WallpaperManager.FLAG_LOCK;
                } else {
                    whichWallpaper = WallpaperManager.FLAG_SYSTEM
                            | WallpaperManager.FLAG_LOCK;
                }

                try {
                    wm.setResource(R.drawable.black, whichWallpaper);
                } catch (IOException e) {
                }

                setResult(RESULT_OK);
                finish();
            }
        })
        .show();
    }
}
