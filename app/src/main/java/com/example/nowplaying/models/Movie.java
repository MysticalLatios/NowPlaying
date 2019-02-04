package com.example.nowplaying.models;

import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String  overview;

    //Create a constructor that will throw any exceptions to what calls the constructor
    public Movie(JSONObject json_in) throws JSONException {
        posterPath = json_in.getString("poster_path");
        title = json_in.getString("title");
        overview = json_in.getString("overview");
    }

    //Create a constructor for json arrays as an input
    public static List<Movie> fromJsonArray(JSONArray array_in) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < array_in.length(); i++){
            movies.add(new Movie(array_in.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath) ;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
