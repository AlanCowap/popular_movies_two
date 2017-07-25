package com.example.android.app.khayapopularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by User on 5/23/2017.
 */
//TODO SUGGESTION Between noybs, user and Khaya this is a real team effort - unfortunately it is for an individual exam :^)

public class NetworkUtils {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private final static String DELIMETER = "\\A";

    private static final String API_QUERY = "api_key";

    private static final String API_KEY = BuildConfig.MY_MOVIEDB_API_KEY;

    public static URL buildUrl(String sortBy) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortBy)
                .appendQueryParameter(API_QUERY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildUrl(String sortBy, String movieid) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(movieid)
                .appendPath(sortBy)
                .appendQueryParameter(API_QUERY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter(DELIMETER);

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
