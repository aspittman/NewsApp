package com.affinityapps.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class NewsListAdapter extends ArrayAdapter<News> {

    public NewsListAdapter(Context context, ArrayList<News> newsArrayList) {
        super(context, 0, newsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News newsPosition = getItem(position);

        TextView articleTitleTextView = convertView.findViewById(R.id.article_title_top);
        TextView sectionNameTextView = convertView.findViewById(R.id.section_name_top);
        TextView authorFirstNameTextView = convertView.findViewById(R.id.author_first_name_bottom);
        TextView authorLastNameTextView = convertView.findViewById(R.id.author_last_name_bottom);
        TextView datePublishedTextView = convertView.findViewById(R.id.date_published_bottom);

        assert newsPosition != null;
        articleTitleTextView.setText(newsPosition.getArticleTitle());
        sectionNameTextView.setText(newsPosition.getSectionName());
        authorFirstNameTextView.setText(newsPosition.getAuthorFirstName());
        authorLastNameTextView.setText(newsPosition.getAuthorLastName());
        datePublishedTextView.setText(newsPosition.getDatePublished());

        return convertView;
    }
}
