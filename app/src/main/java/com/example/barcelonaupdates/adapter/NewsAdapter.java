package com.example.barcelonaupdates.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barcelonaupdates.individual_pages.NewsDetailActivity;
import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private ArrayList<News>news;
    Context context;

    public NewsAdapter(ArrayList<News> news, Context context) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.card_news, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsImg.setImageResource(news.get(position).getPhoto());
        holder.newsTitle.setText(news.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImg;
        TextView newsTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImg = itemView.findViewById(R.id.news_img);
            newsTitle = itemView.findViewById(R.id.news_title);

            itemView.setOnClickListener(e->{
                Intent toNewsDetail = new Intent(context, NewsDetailActivity.class);
                toNewsDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                toNewsDetail.putExtra("News", news.get(getAdapterPosition()));
                context.startActivity(toNewsDetail);
            });

        }
    }
}
