package org.dreamwalker.newsreader.Interface;

import org.dreamwalker.newsreader.Model.News;
import org.dreamwalker.newsreader.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */

public interface NewsService {

    @GET("v1/sources?language=en")
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);

}
