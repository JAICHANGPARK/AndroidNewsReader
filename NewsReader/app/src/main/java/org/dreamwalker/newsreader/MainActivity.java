package org.dreamwalker.newsreader;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.dreamwalker.newsreader.Adapter.ListSourceAdapter;
import org.dreamwalker.newsreader.Common.Common;
import org.dreamwalker.newsreader.Interface.NewsService;
import org.dreamwalker.newsreader.Model.WebSite;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    android.app.AlertDialog dialog; //android.app.AlertDialog

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init cache
        Paper.init(this);
        // 서비스 초기화
        mService = Common.getNewsService();

        // 리사이클러 뷰
        listWebsite = (RecyclerView)findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);

        dialog = new SpotsDialog(this);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebSiteSource(true);
            }
        });


        loadWebSiteSource(false);

    }

    private void loadWebSiteSource(boolean isRefreshed) {

        if (!isRefreshed){
            String cache = Paper.book().read("cache");
            // 만약 캐시에 데이터가 있다면
            if (cache != null && !cache.isEmpty()){
                // 제이선에서 오브젝트로 캐쉬를 변경한다.
                WebSite website = new Gson().fromJson(cache,WebSite.class);
                adapter = new ListSourceAdapter(getBaseContext(), website);
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);

            }else {
                // TODO: 10/10/17 캐쉬에 데이터가 없을 경우 데이터를 서버에서 읽어오는 코드를 작성.
                dialog.show();

                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        dialog.dismiss();
                        adapter = new ListSourceAdapter(getBaseContext(), response.body());
                        adapter.notifyDataSetChanged();
                        listWebsite.setAdapter(adapter);

                        // TODO: 10/10/17 캐쉬에 읽어온 데이터를 저장한다
                        Paper.book().write("cache",new Gson().toJson(response.body()));


                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
            }
        }else {

            dialog.show();
            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    dialog.dismiss();

                    adapter = new ListSourceAdapter(getBaseContext(), response.body());
                    adapter.notifyDataSetChanged();
                    listWebsite.setAdapter(adapter);

                    // TODO: 10/10/17 캐쉬에 읽어온 데이터를 저장한다
                    Paper.book().write("cache",new Gson().toJson(response.body()));

                    swipeRefreshLayout.setRefreshing(false);

                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });
        }
    }

}
