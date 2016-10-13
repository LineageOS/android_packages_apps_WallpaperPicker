/*
 * Copyright (C) 2008 The Android Open Source Project
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
package org.cyanogenmod.wallpaperpicker;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import org.cyanogenmod.wallpaperpicker.util.WallpaperUtils;
import org.cyanogenmod.wallpaperpicker.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ThemeUtilities {

    public static Bitmap getThemeWallpaper(Context context, String path, String pkgName,
                                           boolean thumb) {
        InputStream is = null;
        try {
            Resources res = context.getPackageManager().getResourcesForApplication(pkgName);
            if (res == null) {
                return null;
            }

            AssetManager am = res.getAssets();
            String[] wallpapers = am.list(path);
            if (wallpapers == null || wallpapers.length == 0) {
                return null;
            }
            is = am.open(path + File.separator + wallpapers[0]);

            BitmapFactory.Options bounds = new BitmapFactory.Options();
            bounds.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, bounds);
            if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
                return null;

            int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                    : bounds.outWidth;
            Point outSize;

            if (thumb) {
                outSize = getDefaultThumbnailSize(context.getResources());
            } else {
                outSize = WallpaperUtils.getDefaultWallpaperSize(res,
                        ((Activity) context).getWindowManager());
            }
            int thumbSampleSize = (outSize.y > outSize.x) ? outSize.y : outSize.x;

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = originalSize / thumbSampleSize;
            return BitmapFactory.decodeStream(is, null, opts);
        } catch (IOException e) {
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        } catch (OutOfMemoryError e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Point getDefaultThumbnailSize(Resources res) {
        return new Point(res.getDimensionPixelSize(R.dimen.wallpaperThumbnailWidth),
                res.getDimensionPixelSize(R.dimen.wallpaperThumbnailHeight));
    }
}
