package com.example.barcelonaupdates.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.database.GamesHelper;
import com.example.barcelonaupdates.model.Games;
import com.example.barcelonaupdates.model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History>histories;
    Context context;
    GamesHelper gamesHelper;

    public HistoryAdapter(ArrayList<History> histories, Context context) {
        this.histories = histories;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.card_history, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        // cari game dengan Game ID tersebut
        gamesHelper = new GamesHelper(context);
        int gameId = histories.get(position).getGameId();
        Games game = gamesHelper.getGameByID(gameId);
        // set tulisan sesuai history dan game yang dibeli
        // untuk yg integer diconvert dulu ke string
        holder.historyId.setText("ID: " + String.valueOf(histories.get(position).getHistoryId()));
        holder.qty.setText("Quantity: " + String.valueOf(histories.get(position).getQty()));
        holder.gameName.setText(game.getGameName());
        holder.date.setText(game.getDate());
        holder.time.setText(game.getTime());
        holder.category.setText(game.getCategory());
        holder.totalPrice.setText(String.valueOf(histories.get(position).getTotalPrice()));
    }

    @Override
    public int getItemCount() { return histories.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView historyId, qty, gameName, date, time, category, totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            historyId = itemView.findViewById(R.id.history_id);
            qty = itemView.findViewById(R.id.qty);
            gameName = itemView.findViewById(R.id.game_name);
            date = itemView.findViewById(R.id.game_date);
            time = itemView.findViewById(R.id.game_time);
            category = itemView.findViewById(R.id.game_category);
            totalPrice = itemView.findViewById(R.id.total_price);
        }
    }
}
