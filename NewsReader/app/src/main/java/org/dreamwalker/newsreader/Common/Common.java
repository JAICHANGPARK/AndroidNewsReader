package org.dreamwalker.newsreader.Common;

import android.util.Log;

import org.dreamwalker.newsreader.Interface.IconBetterIdeaService;
import org.dreamwalker.newsreader.Interface.NewsService;
import org.dreamwalker.newsreader.Remote.IconBetterIdeaClient;
import org.dreamwalker.newsreader.Remote.RetrofitClient;

import static android.content.ContentValues.TAG;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";
    public static final String API_KEY = "your API key";


    public static NewsService getNewsService(){
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);

    }

    public static IconBetterIdeaService getIconService(){
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);

    }

    /**
     *  https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=26177c7ba8a54b4b9c46981f1b53a11d
     */
    public static String getAPIUrl(String source, String sortBy, String apiKey){
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v1/articles?source=");
        apiUrl.append(source);
        apiUrl.append("&sortBy=");
        apiUrl.append(sortBy);
        apiUrl.append("&apiKey=");
        apiUrl.append(apiKey);

        Log.e(TAG, "getAPIUrl: " + apiUrl);

        return apiUrl.toString();



    }

}
