package com.example.responsitpm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.responsitpm.Listener.NewsListener;
import com.example.responsitpm.Model.ArticlesItem;
import com.example.responsitpm.R;
import com.example.responsitpm.WebActivity;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<ArticlesItem> articlesItemList;
    private Context context;
    private NewsListener listener;

    public void setModelArticle(List<ArticlesItem> articlesItemList) {
        this.articlesItemList = articlesItemList;
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        final ArticlesItem articlesItem = articlesItemList.get(position);
        holder.tvTitle.setText(articlesItem.getTitle());
        holder.tvAuthor.setText(articlesItem.getAuthor());
//        Glide.with(holder.itemView.getContext())
//                .load(articlesItem.getUrlToImage())
//                .apply(new RequestOptions().override(300,300))
//                .into(holder.ivNews);
        Picasso.get()
                .load(articlesItem.getUrlToImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivNews);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url",articlesItem.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private ImageView ivNews;
        private CardView layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivNews = itemView.findViewById(R.id.ivNews);
            layout = itemView.findViewById(R.id.itemLayout);
        }
    }

    public void setListener(NewsListener listener) {
        this.listener = listener;
    }
}
