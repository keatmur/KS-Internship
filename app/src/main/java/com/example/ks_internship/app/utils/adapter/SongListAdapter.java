package com.example.ks_internship.app.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.ks_internship.R;
import com.example.ks_internship.app.model.DeezerTrack;

import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private ArrayList<DeezerTrack> items;
    private Context ctx;
    private OnSongRecycleClickListener listener;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public SongListAdapter(ArrayList<DeezerTrack> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swip_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {




        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(position));

        holder.frontLayout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(v, holder.getAdapterPosition());
            }

        });
        holder.delete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDeleteClick(v, holder.getAdapterPosition());
            }
        });
        holder.share.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemShareClick(v, holder.getAdapterPosition());
            }
        });
        holder.ebit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemEbitClick(v, holder.getAdapterPosition());
                viewBinderHelper.closeLayout(String.valueOf(position));
            }
        });

        holder.name_song.setText(items.get(position).getTitle());
        holder.singer_song.setText(items.get(position).getArtist().getName());

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

    public ArrayList<DeezerTrack> getItems() {
        return items;
    }

    public void setItems(ArrayList<DeezerTrack> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public SwipeRevealLayout swipeRevealLayout;
        CardView cv;
        AppCompatTextView name_song;
        AppCompatTextView singer_song;
        View frontLayout;
        AppCompatImageView delete;
        AppCompatImageView share;
        AppCompatImageView ebit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            swipeRevealLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            cv = itemView.findViewById(R.id.cv);
            name_song = itemView.findViewById(R.id.name_song);
            singer_song = itemView.findViewById(R.id.singer_song);
            frontLayout = itemView.findViewById(R.id.front_layout);
            delete = itemView.findViewById(R.id.deleteBtn);
            share = itemView.findViewById(R.id.shareBtn);
            ebit = itemView.findViewById(R.id.ebitBtn);


        }


    }
}

