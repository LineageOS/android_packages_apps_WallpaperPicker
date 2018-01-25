package com.android.wallpaperpicker.common;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.android.wallpaperpicker.R;

/**
 * Utility class used to show dialogs for things like picking which wallpaper to set.
 */
public class DialogUtils {
    /**
     * Calls cropTask.execute(), once the user has selected which wallpaper to set. On pre-N
     * devices, the prompt is not displayed since there is no API to set the lockscreen wallpaper.
     *
     * TODO: Don't use CropAndSetWallpaperTask on N+, because the new API will handle cropping instead.
     */
    public static void executeCropTaskAfterPrompt(
            Context context, final AsyncTask<Integer, ?, ?> cropTask,
            DialogInterface.OnCancelListener onCancelListener) {
        if (Utilities.isAtLeastN()) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.wallpaper_instructions)
                    .setItems(R.array.which_wallpaper_options_cm, new DialogInterface.OnClickListener() {
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
                        cropTask.execute(whichWallpaper);
                    }
                })
                .setOnCancelListener(onCancelListener)
                .show();
        } else {
            cropTask.execute(WallpaperManager.FLAG_SYSTEM);
        }
    }
}
