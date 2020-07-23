package com.affinityapps.newsapp;

public class News {

    private String articleTitle;
    private String sectionName;
    private String authorName;
    private String datePublished;
    private String newsStoryUrl;


    public News(String articleTitle, String sectionName, String authorName, String datePublished, String newsStoryUrl) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.authorName = authorName;
        this.datePublished = datePublished;
        this.newsStoryUrl = newsStoryUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getNewsStoryUrl() {
        return newsStoryUrl;
    }

    public void setNewsStoryUrl(String newsStoryUrl) {
        this.newsStoryUrl = newsStoryUrl;
    }
}
