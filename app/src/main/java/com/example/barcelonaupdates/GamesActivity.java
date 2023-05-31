package com.example.barcelonaupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.barcelonaupdates.adapter.GamesAdapter;
import com.example.barcelonaupdates.database.GamesHelper;
import com.example.barcelonaupdates.model.Games;

import java.util.ArrayList;

public class GamesActivity extends AppCompatActivity {

    GamesHelper gamesHelper = new GamesHelper(this);
    ArrayList<Games> games = new ArrayList<>();
    RecyclerView gamesRv;
    GamesAdapter gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // insert dulu kalau kosong, kalau tidak biarkan
        if(gamesHelper.getGameCount()==0){
            gamesHelper.listGames();
        }

        // dapat semua list games
        games = gamesHelper.getAllGames();

        gamesRv = findViewById(R.id.games_rv);
        gamesAdapter = new GamesAdapter(games, this);
        gamesRv.setAdapter(gamesAdapter);
        gamesRv.setLayoutManager(new LinearLayoutManager(this));
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