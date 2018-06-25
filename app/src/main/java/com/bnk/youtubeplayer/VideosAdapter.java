package com.bnk.youtubeplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class VideosAdapter extends ArrayAdapter {
    public VideosAdapter(Activity context, ArrayList<VideoData> videoData) {
        super(context,0,videoData);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.simple_list_item, parent, false);
        }
        VideoData currentVideo = (VideoData) getItem(position);

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(currentVideo.getTitle());

        return listItemView;
    }

}
