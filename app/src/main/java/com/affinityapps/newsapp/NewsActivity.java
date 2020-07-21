package com.affinityapps.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ArrayList<News> arrayList = new ArrayList<>();
        arrayList.add(new News("Title", "Section", "Author", "Date"));
        arrayList.add(new News("Title", "Section", "Author", "Date"));
        arrayList.add(new News("Title", "Section", "Author", "Date"));
        arrayList.add(new News("Title", "Section", "Author", "Date"));
        arrayList.add(new News("Title", "Section", "Author", "Date"));

        NewsListAdapter adapter = new NewsListAdapter(NewsActivity.this, arrayList);

        ListView listView = (ListView) findViewById(R.id.news_list);

        listView.setAdapter(adapter);
    }
}