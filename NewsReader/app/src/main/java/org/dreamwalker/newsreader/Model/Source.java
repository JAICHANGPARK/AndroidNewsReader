package org.dreamwalker.newsreader.Model;

import java.util.List;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */

class UrlsToLogos {
    private String small, medium, large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}

public class Source {

    /*

    "id": "abc-news-au",
    "name": "ABC News (AU)",
    "description": n-depth analysis, the latest business, sport, weather and more.",
    "url": "http://www.abc.net.au/news",
    "category": "general",
    "language": "en",
    "country": "au",
    -"urlsToLogos": {
            "small": "",
            "medium": "",
            "large": ""
},
-"sortBysAvailable": [
"top"
]
     */

    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private UrlsToLogos urlsToLogos;
    private List<String> sortBysAvailable;

    public Source() {
    }

    public Source(String id, String name, String description, String url, String category, String language, String country, UrlsToLogos urlToLogos, List<String> sortByAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
        this.urlsToLogos = urlToLogos;
        this.sortBysAvailable = sortByAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UrlsToLogos getUrlToLogos() {
        return urlsToLogos;
    }

    public void setUrlToLogos(UrlsToLogos urlToLogos) {
        this.urlsToLogos = urlToLogos;
    }

    public List<String> getSortByAvailable() {
        return sortBysAvailable;
    }

    public void setSortByAvailable(List<String> sortByAvailable) {
        this.sortBysAvailable = sortByAvailable;
    }
}
