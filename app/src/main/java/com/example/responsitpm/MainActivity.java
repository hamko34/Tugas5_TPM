package com.example.responsitpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.responsitpm.API.ApiClient;
import com.example.responsitpm.API.ApiInterface;
import com.example.responsitpm.Adapter.NewsAdapter;
import com.example.responsitpm.Model.ArticlesItem;
import com.example.responsitpm.Model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "df37909314ce4d6aacf66076297bee07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rvNews = findViewById(R.id.rvNews);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        rvNews.setLayoutManager(linearLayoutManager);

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseModel> call = apiService.getLatestNews("us",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("ok")){
                    List<ArticlesItem> articlesItemList = response.body().getArticles();

                    if (articlesItemList.size() > 0){
                        final NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this);
                        newsAdapter.setModelArticle(articlesItemList);
                        rvNews.setAdapter(newsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("out",t.toString());
            }
        });


    }
}
