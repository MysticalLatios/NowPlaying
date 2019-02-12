package com.example.nowplaying.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    String releaseDate;
    int id;
    double rating;

    //Create a constructor that will throw any exceptions to what calls the constructor
    public Movie(JSONObject json_in) throws JSONException {
        posterPath = json_in.getString("poster_path");
        backdropPath = json_in.getString("backdrop_path");
        title = json_in.getString("title");
        overview = json_in.getString("overview");
        releaseDate = json_in.getString("release_date");
        rating = json_in.getDouble("vote_average");
        id = json_in.getInt("id");
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

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath) ;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating/2;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.rating);
        dest.writeInt(this.id);
    }

    protected Movie(Parcel in) {
        this.posterPath = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.rating = in.readDouble();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
