package com.affinityapps.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String COMPLETE_GUARDIAN_API_URL = "https://content.guardianapis.com/search?q=technology&show-tags=contributor&api-key=acc7bb9b-e342-4356-abab-c210c6f4807e";
    private static final String QUERY_PARAM = "q";
    private static final String SHOW_TAGS_PARAM = "show-tags";
    private static final String API_KEY_PARAM = "api-key";
    private static final int NEWS_LOADER_ID = 0;

    private NewsListAdapter newsListAdapter;
    private TextView emptyListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView newsListView = (ListView) findViewById(R.id.news_list);

        emptyListTextView = (TextView) findViewById(R.id.blank_view_message);
        newsListView.setEmptyView(emptyListTextView);

        newsListAdapter = new NewsListAdapter(NewsActivity.this, new ArrayList<News>());
        newsListView.setAdapter(newsListAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News newsStory = newsListAdapter.getItem(position);

                assert newsStory != null;
                Uri newsStoryUri = Uri.parse(newsStory.getNewsStoryUrl());

                Intent newsStoryIntent = new Intent(Intent.ACTION_VIEW, newsStoryUri);

                startActivity(newsStoryIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getSupportLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            emptyListTextView.setText(R.string.no_internet_connection);
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter(QUERY_PARAM, "technology")
                .appendQueryParameter(SHOW_TAGS_PARAM, "contributor")
                .appendQueryParameter(API_KEY_PARAM, "acc7bb9b-e342-4356-abab-c210c6f4807e");

        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyListTextView.setText(R.string.no_display);

        if (data != null && !data.isEmpty()) {
            newsListAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        newsListAdapter.clear();
    }
}