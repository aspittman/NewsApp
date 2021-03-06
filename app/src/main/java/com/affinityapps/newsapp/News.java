package com.affinityapps.newsapp;

public class News {

    private String articleTitle;
    private String sectionName;
    private String authorFirstName;
    private String authorLastName;
    private String datePublished;
    private String newsStoryUrl;


    public News() {
    }

    public News(String authorFirstName, String authorLastName) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public News(String articleTitle, String sectionName, String authorFirstName, String authorLastName, String datePublished, String newsStoryUrl) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
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
