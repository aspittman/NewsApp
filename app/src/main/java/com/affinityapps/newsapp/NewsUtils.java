package com.affinityapps.newsapp;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NewsUtils {

    private static final String LOG_TAG = NewsUtils.class.getSimpleName();

    private NewsUtils() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<News> fetchNewsStoryData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractFeatureFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving news story JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String newsJson) {

        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        List<News> newsList = new ArrayList<>();

        try {
            JSONObject newsParser = new JSONObject(newsJson);
            JSONObject jsonResponse = newsParser.getJSONObject("response");
            JSONArray jsonNewsArray = jsonResponse.getJSONArray("results");

            for (int i = 0; i < jsonNewsArray.length(); i++) {

                JSONObject jsonObject = jsonNewsArray.getJSONObject(i);
                String jsonArticleTitle = jsonObject.getString("webTitle");
                String jsonSectionName = jsonObject.getString("sectionName");
                String jsonAuthorName = jsonObject.getString("pillarId");
                String jsonDatePublished = jsonObject.getString("webPublicationDate");
                String jsonWebUrl = jsonObject.getString("webUrl");

                newsList.add(new News(jsonArticleTitle, jsonSectionName, jsonAuthorName, jsonDatePublished, jsonWebUrl));
            }

        } catch (JSONException e) {
            Log.e("NewsUtils", "Problem parsing News Story JSON results", e);
        }
        return newsList;
    }
}
