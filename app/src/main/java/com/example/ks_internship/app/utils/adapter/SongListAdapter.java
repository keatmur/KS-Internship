package com.example.ks_internship.app.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.app.model.Song;
import com.example.ks_internship.app.utils.lisners.OnSongListener;
import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private ArrayList<Song> items;
    private Context ctx;
    private OnSongRecycleClickListener listener;

    public SongListAdapter(ArrayList<Song> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        view.setOnLongClickListener(view1 -> {
            if (listener != null) {
                listener.onItemLongClick(view, viewHolder.getAdapterPosition());
            }
            return true;
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name_song.setText(items.get(position).getName());
        holder.singer_song.setText(items.get(position).getSinger());

    }

    public OnSongRecycleClickListener getListener() {
        return listener;
    }

    public void setListener(OnSongRecycleClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<Song> getItems() {
        return items;
    }

    public void setItems(ArrayList<Song> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        AppCompatTextView name_song;
        AppCompatTextView singer_song;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);
            name_song = itemView.findViewById(R.id.name_song);
            singer_song = itemView.findViewById(R.id.singer_song);

        }
    }
}
