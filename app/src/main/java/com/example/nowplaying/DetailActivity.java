package com.example.nowplaying;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nowplaying.models.Movie;
import com.example.nowplaying.R;

public class DetailActivity extends AppCompatActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyDMO4gyidU6I_g9bx85xFzYYa-eX687g38";


    TextView tvTitle;
    TextView tvOverview;
    TextView tvDate;
    RatingBar rbRatting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvDate = findViewById(R.id.tvDate);
        rbRatting = findViewById(R.id.rbRatting);

        Movie movie = getIntent().getParcelableExtra("movie");

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText(movie.getReleaseDate());
        rbRatting.setRating(((float) movie.getRating()));
    }
}
