package org.cyanogenmod.wallpaperpicker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Central list of files the Launcher writes to the application data directory.
 *
 * To add a new Launcher file, create a String constant referring to the filename, and add it to
 * ALL_FILES, as shown below.
 */
public class PickerFiles {

    private static final String XML = ".xml";

    public static final String DEFAULT_WALLPAPER_THUMBNAIL = "default_thumb2.jpg";
    public static final String DEFAULT_WALLPAPER_THUMBNAIL_OLD = "default_thumb.jpg";
    public static final String WALLPAPER_CROP_PREFERENCES_KEY =
            "org.cyanogenmod.wallpaperpicker.WallpaperCropActivity";

    public static final String WALLPAPER_IMAGES_DB = "saved_wallpaper_images.db";

    public static final List<String> ALL_FILES = Collections.unmodifiableList(Arrays.asList(
            DEFAULT_WALLPAPER_THUMBNAIL,
            DEFAULT_WALLPAPER_THUMBNAIL_OLD,
            WALLPAPER_CROP_PREFERENCES_KEY + XML,
            WALLPAPER_IMAGES_DB));

}
