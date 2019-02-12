package com.example.nowplaying;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nowplaying.models.Movie;
import com.example.nowplaying.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyDMO4gyidU6I_g9bx85xFzYYa-eX687g38";
    //Setting up to replace the %d, wich is the movie ID
    public static final String TRAILERS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    TextView tvTitle;
    TextView tvOverview;
    TextView tvDate;
    RatingBar rbRatting;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvDate = findViewById(R.id.tvDate);
        rbRatting = findViewById(R.id.rbRatting);
        youTubePlayerView = findViewById(R.id.player);

        Movie movie = getIntent().getParcelableExtra("movie");

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText(movie.getReleaseDate());
        rbRatting.setRating(((float) movie.getRating()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(TRAILERS_URL, movie.getId()), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray results = response.getJSONArray("results");
                    //Make sure we actually have some videos
                    if (results.length() == 0){
                        return;
                    }
                    JSONObject movieTrailer = results.getJSONObject(0);
                    String youtubeKey = movieTrailer.getString("key");
                    initializeYoutube(youtubeKey);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


    }

    private void initializeYoutube(final String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
