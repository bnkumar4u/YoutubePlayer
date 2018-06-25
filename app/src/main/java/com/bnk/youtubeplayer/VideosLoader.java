package com.bnk.youtubeplayer;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;


public class VideosLoader extends AsyncTaskLoader {

    /** Query URL */
    private String mUrl;

    public VideosLoader(Context context, String url) {
        super(context);
        mUrl= url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<VideoData> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<VideoData> videoDatas = QueryUtils.fetchEarthquakeData(mUrl);

        return videoDatas;
    }
}
