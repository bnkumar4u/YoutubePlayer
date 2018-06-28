package com.bnk.youtubeplayer;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<VideoData>> {


    private static final String YOUTUBE_PLAYLIST_BASE_URL="https://www.googleapis.com/youtube/v3/playlistItems";
    public static final String LOG_TAG = MainActivity.class.getName();
    private static final int YOUTUBE_LOADER_ID=1;

    /** Adapter for the list of videos */
    private VideosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new VideosAdapter(this,new ArrayList<VideoData>());

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //get reference to the Connectivity manager to check state of network
        ConnectivityManager connMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //get details on currently active pic1 data network
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if(networkInfo!=null&&networkInfo.isConnected())
        {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(YOUTUBE_LOADER_ID, null, this);
        }
        else
        {
            //otherwise display error
            //first ,hide loading indicator so error message will be visible

            //update empty stte with no connection error message
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                VideoData currentVideoData = (VideoData) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,YVPlayerActivity.class);
                if (currentVideoData != null) {
                    intent.putExtra("videoId",currentVideoData.getVideoId());
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Video not found",Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public Loader<ArrayList<VideoData>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(YOUTUBE_PLAYLIST_BASE_URL);
        Uri.Builder  uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("part","snippet,contentDetails");
        uriBuilder.appendQueryParameter("maxResults","50")
                .appendQueryParameter("playlistId","PLsyeobzWxl7pFZoGT1NbZJpywedeyzyaf")
                .appendQueryParameter("fields","pageInfo,nextPageToken,items/snippet(title,description,thumbnails),items/contentDetails/videoId")
                .appendQueryParameter("key","AIzaSyBd2gGjpgdsYC42-E7I6LdjJsJfZWq_yrg");

        return new VideosLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<VideoData>> loader, ArrayList<VideoData> videoData) {
        adapter.clear();
        if (videoData != null && !videoData.isEmpty())
        {
            adapter.addAll(videoData);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<VideoData>> loader) {

        //Loader reset, so we can clear out our exiting data.
        adapter.clear();
    }
}
