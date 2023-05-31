package com.example.barcelonaupdates.individual_pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcelonaupdates.GamesActivity;
import com.example.barcelonaupdates.HomeActivity;
import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.database.GamesHelper;
import com.example.barcelonaupdates.database.HistoryHelper;
import com.example.barcelonaupdates.model.Games;
import com.example.barcelonaupdates.model.History;

import java.util.ArrayList;

public class GameDetailActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int userId, gameId;
    Games game;
    GamesHelper gamesHelper = new GamesHelper(this);
    TextView gameName, date, time, category, price;
    EditText qtyEt;
    Button purchaseBtn;
    ArrayList<History> histories = new ArrayList<>();
    HistoryHelper historyHelper = new HistoryHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getInt("UserID", 0); // id user buat sambung table history dan user
        gameId = getIntent().getIntExtra("GameID", 0); // id game buat sambung table history dan game yang dibeli tiketnya
        game = gamesHelper.getGameByID(gameId);

        gameName = findViewById(R.id.game_name);
        date = findViewById(R.id.game_date);
        time = findViewById(R.id.game_time);
        category = findViewById(R.id.game_category);
        price = findViewById(R.id.game_price);
        qtyEt = findViewById(R.id.qty_et);
        purchaseBtn = findViewById(R.id.purchase_btn);

        gameName.setText(game.getGameName());
        date.setText(game.getDate());
        time.setText(game.getTime());
        category.setText(game.getCategory());
        price.setText("Rp" + String.valueOf(game.getPrice()));

        purchaseBtn.setOnClickListener(e->{
            String qty = qtyEt.getText().toString();
            if(qty.equals("")){ // kosong
                Toast.makeText(this, "Quantity must be filled!", Toast.LENGTH_SHORT).show();
            } else if (Integer.parseInt(qty)<0) { // input < 0
                Toast.makeText(this, "Invalid quantity!", Toast.LENGTH_SHORT).show();
            }else {
                // add to history
                histories = historyHelper.getAllHistories();
                int qtyInt = Integer.parseInt(qty);
                int totalPrice = qtyInt*game.getPrice();
                History history = new History(histories.size()+1, userId, gameId, qtyInt, totalPrice);
                historyHelper.insertHistory(history);
                Toast.makeText(this, "Ticket(s) has been purchased!", Toast.LENGTH_SHORT).show();
                Intent toGames = new Intent(this, GamesActivity.class);
                startActivity(toGames);
            }
        });

    }

    // back arrow
    public boolean onOptionsItemSelected(MenuItem item){
        Intent toGames = new Intent(this, GamesActivity.class);
        startActivityForResult(toGames, 0);
        return true;
    }
}