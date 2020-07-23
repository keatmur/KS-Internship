package com.example.ks_internship.app.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.app.activity.ThirdActivity;
import com.example.ks_internship.app.model.Song;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.adapter.SongListAdapter;
import com.example.ks_internship.app.utils.lisners.OnSongListener;
import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ChoiceFragment extends Fragment {

    public OnSongListener onSongListener;
    private RecyclerView recyclerView;
    private ArrayList<Song> songArrayList;
    private SongListAdapter songListAdapter;
    private FloatingActionButton addFab;

    private OnSongRecycleClickListener onSongRecycleClickListener = new OnSongRecycleClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            onSongListener.setSong(songListAdapter.getItems().get(position).toString());
        }

        @Override
        public void onItemLongClick(View v, int position) {
            new AlertDialog.Builder(v.getContext())
                    .setMessage("Do you really want to delete?")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          songArrayList.remove(position);
                          songListAdapter.notifyDataSetChanged();


                        }
                    })
                    .setNegativeButton("Отмена", null)
                    .create().show();


        }


    };


    public ChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choice, container, false);
        recyclerView = v.findViewById(R.id.my_recycler_view);
        addFab = v.findViewById(R.id.addFab);
        addFab.setOnClickListener(i -> {
            Intent intent = new Intent(v.getContext(), ThirdActivity.class);
            startActivityForResult(intent, Constants.RESULT_COD);
        });

        initSongAdapter(v);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(songListAdapter);


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RESULT_COD) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    songArrayList.add(data.getExtras().getParcelable(Constants.EXTRA_MESSAGE));
                    songListAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(recyclerView.getBottom());


                }
            }
        }
    }

    private void initSongAdapter(View v) {
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song("Beautiful People", "Ed Sheeran ", " Pop music", "No.6 Collaborations Project"));
        songArrayList.add(new Song("Level of Concern", "Twenty One Pilot", "Alternative music / indi", "Level of Concern"));
        songArrayList.add(new Song("21 Guns", "Green Day", "Alternative music / indi", "21st Century Breakdown"));
        songArrayList.add(new Song("Viva La Vida", "Coldplay", "Pop music", "Viva La Vida (Prospekt's March Edition)"));
        songArrayList.add(new Song("Come As You Are ", "Nirvana", "Альтернативна музика/інді, Рок", "Nevermind"));

        songListAdapter = new SongListAdapter(songArrayList, v.getContext());
        songListAdapter.setListener(onSongRecycleClickListener);


    }


    public void setOnSongListener(OnSongListener onSongListener) {
        this.onSongListener = onSongListener;
    }
}