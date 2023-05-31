package com.example.barcelonaupdates.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.individual_pages.GameDetailActivity;
import com.example.barcelonaupdates.model.Games;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder>{

    private ArrayList<Games>games;
    Context context;

    public GamesAdapter(ArrayList<Games> games, Context context) {
        this.games = games;
        this.context = context;
    }

    @NonNull
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.card_games, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder holder, int position) {
        holder.gameName.setText(games.get(position).getGameName());
        holder.date.setText(games.get(position).getDate());
        holder.time.setText(games.get(position).getTime());
        holder.category.setText(games.get(position).getCategory());
    }

    @Override
    public int getItemCount() { return games.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView gameName, date, time, category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameName = itemView.findViewById(R.id.game_name);
            date = itemView.findViewById(R.id.game_date);
            time = itemView.findViewById(R.id.game_time);
            category = itemView.findViewById(R.id.game_category);

            itemView.setOnClickListener(e->{
                Intent toGameDetail = new Intent(context, GameDetailActivity.class);
                toGameDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                toGameDetail.putExtra("GameID", games.get(getAdapterPosition()).getGameId());
                context.startActivity(toGameDetail);
            });

        }
    }
}
