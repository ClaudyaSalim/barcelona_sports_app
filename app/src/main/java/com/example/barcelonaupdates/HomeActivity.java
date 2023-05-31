package com.example.barcelonaupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.barcelonaupdates.adapter.NewsAdapter;
import com.example.barcelonaupdates.database.UserHelper;
import com.example.barcelonaupdates.model.News;
import com.example.barcelonaupdates.model.User;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView nameTv;
    SharedPreferences sharedPreferences;
    UserHelper userHelper = new UserHelper(this);
    int userId;
    User user;
    RecyclerView newsRv;
    ArrayList<News>news = new ArrayList<>();
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameTv = findViewById(R.id.name_tv);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getInt("UserID", 0);
        user = userHelper.getUserByID(userId);
        nameTv.setText("Hello " +  user.getUserName() + " !");

        // add news
        news.add(new News(1, R.string.news_title1, R.drawable.logo_barcelona_tv, R.string.news_desc1));
        news.add(new News(2, R.string.news_title2, R.drawable.xavi_barcelona, R.string.news_desc2));
        news.add(new News(3, R.string.news_title3, R.drawable.lamine_yamal_1, R.string.news_desc3));
        news.add(new News(4, R.string.news_title4, R.drawable.lamine_yamal_2, R.string.news_desc4));
        news.add(new News(5, R.string.news_title5, R.drawable.barcelona_players, R.string.news_desc5));

        newsRv = findViewById(R.id.news_rv);
        newsAdapter = new NewsAdapter(news, this);
        newsRv.setAdapter(newsAdapter);
        newsRv.setLayoutManager(new LinearLayoutManager(this));

    }

    // buat masukin file menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selected = item.getItemId();
        if(selected == R.id.news_menu){
            Intent inNews = new Intent(this, HomeActivity.class);
            startActivity(inNews);
        } else if (selected == R.id.games_menu) {
            Intent toGames = new Intent(this, GamesActivity.class);
            startActivity(toGames);
        } else if (selected == R.id.history_menu) {
            Intent toHistory = new Intent(this, HistoryActivity.class);
            startActivity(toHistory);
        } else if (selected == R.id.logout_menu) {
            Intent toLogin = new Intent(this, MainActivity.class);
            startActivity(toLogin);
        }
        return true;
    }
}