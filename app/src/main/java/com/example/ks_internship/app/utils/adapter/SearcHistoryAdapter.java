package com.example.ks_internship.app.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.lisners.OnSearchHistoryListener;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class SearcHistoryAdapter  extends RecyclerView.Adapter<SearcHistoryAdapter.ViewHolder>{

    private List<String> arrayList;
    private OnSearchHistoryListener listener;

    public SearcHistoryAdapter(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public OnSearchHistoryListener getListener() {
        return listener;
    }

    public void setListener(OnSearchHistoryListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearcHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_seqrch_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearcHistoryAdapter.ViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position));
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.titleSearch(view, arrayList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name_song);
        }
    }
}
