package org.dreamwalker.newsreader.Model;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */

public class Article {


    /*
            "author": "Anouk Vleugels",
            "title": "How to get excited about bookkeeping – and other things that spur procrastination",
            "description": "Three proven strategies that help fuel your willpower while battling the paralyzing effects of procrastination.",
            "url": "https://thenextweb.com/full-stack/2017/10/10/how-to-get-excited-about-bookkeeping-and-other-things-that-spur-procrastination/",
            "urlToImage": "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/10/bookkeeping-procrastination-social.jpg",
            "publishedAt": "2017-10-10T12:38:00Z"

    */


    private String author,title,description,url,urlToImage,publishedAt;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


}
