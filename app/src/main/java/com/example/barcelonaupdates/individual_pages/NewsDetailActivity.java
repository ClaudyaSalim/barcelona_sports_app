package com.example.barcelonaupdates.individual_pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barcelonaupdates.HomeActivity;
import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.model.News;

public class NewsDetailActivity extends AppCompatActivity {

    News news;
    TextView newsTitle, newsDesc;
    ImageView newsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        news = (News)getIntent().getSerializableExtra("News");

        newsTitle = findViewById(R.id.news_title);
        newsDesc = findViewById(R.id.news_desc);
        newsImg = findViewById(R.id.news_img);

        newsTitle.setText(news.getTitle());
        newsImg.setImageResource(news.getPhoto());
        newsDesc.setText(news.getNewsDesc());
    }

    // back arrow
    public boolean onOptionsItemSelected(MenuItem item){
        Intent toHome = new Intent(this, HomeActivity.class);
        startActivityForResult(toHome, 0);
        return true;
    }
}