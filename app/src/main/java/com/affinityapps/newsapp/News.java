package com.affinityapps.newsapp;

public class News {

    private String articleTitle;
    private String sectionName;
    private String authorName;
    private String datePublished;


    public News(String articleTitle, String sectionName, String authorName, String datePublished) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.authorName = authorName;
        this.datePublished = datePublished;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDatePublished() {
        return datePublished;
    }
}
