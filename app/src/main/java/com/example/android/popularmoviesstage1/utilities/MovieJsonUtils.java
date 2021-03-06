package com.example.android.popularmoviesstage1.utilities;

import android.content.Context;

import com.example.android.popularmoviesstage1.Movies;
import com.example.android.popularmoviesstage1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class MovieJsonUtils {

    public static List<Movies> getMovieThumbnail(String mainJson, Context context) throws JSONException {

        final String ERROR = "status_code";

        final String RESULTS = "results";
        final String TITLE = "original_title";
        final String POSTER = "poster_path";
        final String PLOT = "overview";
        final String RATING = "vote_average";
        final String RELEASE_DATE = "release_date";

        JSONObject root = new JSONObject(mainJson);

        if (root.has(ERROR)) {
            return null;
        }

        JSONArray results;
        if (root.has(RESULTS))
            results = root.getJSONArray(RESULTS);
        else
            return null;

        List<Movies> movies = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);

            String title = result.optString(TITLE, context.getString(R.string.movie_title_placeholder));
            String poster = result.optString(POSTER, "");
            String plot = result.optString(PLOT, context.getString(R.string.movie_plot_placeholder));
            Double rating = result.optDouble(RATING, -1.0);
            String releaseDate = result.optString(RELEASE_DATE, "N/A");

            movies.add(new Movies(title, poster, plot, rating, releaseDate));
        }

        return movies;
    }
}