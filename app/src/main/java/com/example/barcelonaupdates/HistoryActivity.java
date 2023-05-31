package com.example.barcelonaupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.barcelonaupdates.adapter.HistoryAdapter;
import com.example.barcelonaupdates.database.HistoryHelper;
import com.example.barcelonaupdates.model.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    HistoryHelper historyHelper = new HistoryHelper(this);
    ArrayList<History>histories = new ArrayList<>();
    RecyclerView historyRv;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // ambil user ID dari shared preferences
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("UserID", 0);
        histories = historyHelper.getAllHistoriesbyUser(userId); // dapetin history khusus user tersebut
        ArrayList<History>all = historyHelper.getAllHistories();
        for (History history:
             all) {
            Log.e("Transaction ID", String.valueOf(history.getHistoryId()));
        }
        historyRv = findViewById(R.id.history_rv);
        historyAdapter = new HistoryAdapter(histories, this);
        historyRv.setAdapter(historyAdapter);
        historyRv.setLayoutManager(new LinearLayoutManager(this));

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