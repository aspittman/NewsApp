package com.affinityapps.newsapp;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String newsUrl;

    public NewsLoader(Context context, String newsUrl) {
        super(context);
        this.newsUrl = newsUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public List<News> loadInBackground() {
        if (newsUrl == null) {
            return null;
        }
        return NewsUtils.fetchNewsStoryData(newsUrl);
    }
}
