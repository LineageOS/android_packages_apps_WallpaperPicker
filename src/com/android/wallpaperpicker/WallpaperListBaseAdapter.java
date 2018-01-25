/*
 * Copyright (C) 2018 The LineageOS Project
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

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WallpaperListBaseAdapter extends BaseAdapter {

    private final Context context;

    private static Pair<Integer, Integer>[] DIALOG_ENTRIES = new Pair[] {
        new Pair(R.drawable.ic_home_screen, R.string.which_wallpaper_option_home_screen_cm),
        new Pair(R.drawable.ic_lock_screen, R.string.which_wallpaper_option_lock_screen_cm),
        new Pair(R.drawable.ic_home_screen_and_lock_screen,
                R.string.which_wallpaper_option_home_screen_and_lock_screen_cm)
    };

    private class ViewHolder {
        private ImageView icon;
        private TextView destination;
    }

    public WallpaperListBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(context);
            view = inflator.inflate(R.layout.dialog_set_wallpaper_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.icon = view.findViewById(R.id.icon);
            viewHolder.destination = view.findViewById(R.id.destination);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.icon.setImageDrawable(context.getDrawable(DIALOG_ENTRIES[position].first));
        holder.destination.setText(DIALOG_ENTRIES[position].second);
        return view;
    }

    @Override
    public int getCount() {
        return DIALOG_ENTRIES.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
