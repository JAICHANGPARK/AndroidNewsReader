package org.dreamwalker.newsreader;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import org.dreamwalker.newsreader.Adapter.ListNewsAdapter;
import org.dreamwalker.newsreader.Common.Common;
import org.dreamwalker.newsreader.Interface.NewsService;
import org.dreamwalker.newsreader.Model.Article;
import org.dreamwalker.newsreader.Model.News;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {

    private static final String TAG = "ListNews";

    DiagonalLayout diagonalLayout;
    KenBurnsView kenBurnsView;
    AlertDialog dialog;
    NewsService newsService;
    TextView topAuthor, topTitle;
    SwipeRefreshLayout swipeRefreshLayout;


    String source = "", sortBy = "", webHotUrl = "";

    ListNewsAdapter adapter;
    RecyclerView listNews;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        newsService = Common.getNewsService();

        dialog = new SpotsDialog(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipRefresh);
        diagonalLayout = (DiagonalLayout) findViewById(R.id.diagonalLayout);
        kenBurnsView = (KenBurnsView) findViewById(R.id.top_image);

        topAuthor = (TextView) findViewById(R.id.topAuthor);
        topTitle = (TextView) findViewById(R.id.topTitle);

        listNews = (RecyclerView) findViewById(R.id.list_News);
        listNews.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager = new LinearLayoutManager(this);
        listNews.setLayoutManager(llm);

        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), DetailArticle.class);
                intent.putExtra("webURL", webHotUrl);
                startActivity(intent);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source, true);
            }
        });

        if (getIntent() != null) {
            source = getIntent().getStringExtra("source");
            sortBy = getIntent().getStringExtra("sortBy");
            if (!source.isEmpty() && !sortBy.isEmpty()) {
                loadNews(source, false);
            }
        }
//
//        listNews = (RecyclerView) findViewById(R.id.listNews);
//        listNews.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        listNews.setLayoutManager(layoutManager);


    }

    private void loadNews(String source, boolean isRefreshed) {

        if (!isRefreshed) {
            dialog.show();

            newsService.getNewestArticles(Common.getAPIUrl(source, sortBy, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();

                    Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kenBurnsView);
                    topTitle.setText(response.body().getArticles().get(0).getTitle());
                    topAuthor.setText(response.body().getArticles().get(0).getAuthor());

                    webHotUrl = response.body().getArticles().get(0).getUrl();
                    Log.e(TAG, "onResponse: " + response.body().getArticles());


                    List<Article> removeFirstItem = response.body().getArticles();
                    removeFirstItem.remove(0);

                    adapter = new ListNewsAdapter(removeFirstItem, getBaseContext());
                    adapter.notifyDataSetChanged();
                    listNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });

        }else {
            dialog.show();
            newsService.getNewestArticles(Common.getAPIUrl(source, sortBy, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kenBurnsView);
                    topTitle.setText(response.body().getArticles().get(0).getTitle());
                    topAuthor.setText(response.body().getArticles().get(0).getAuthor());

                    webHotUrl = response.body().getArticles().get(0).getUrl();

                    List<Article> removeFirstItem = response.body().getArticles();
                    removeFirstItem.remove(0);
                    adapter = new ListNewsAdapter(removeFirstItem, getBaseContext());
                    adapter.notifyDataSetChanged();
                    listNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
            swipeRefreshLayout.setRefreshing(false);
        }

    }
}
