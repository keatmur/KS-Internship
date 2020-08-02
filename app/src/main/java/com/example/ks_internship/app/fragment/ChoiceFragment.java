package com.example.ks_internship.app.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ks_internship.R;

import com.example.ks_internship.app.api.ApiCallback;
import com.example.ks_internship.app.api.RestClient;
import com.example.ks_internship.app.app.AppKsInternship;
import com.example.ks_internship.app.database.AppDatabase;
import com.example.ks_internship.app.model.DeezerRepoErrorItem;
import com.example.ks_internship.app.model.DeezerResponse;
import com.example.ks_internship.app.model.DeezerTrack;

import com.example.ks_internship.app.utils.KeyboardUtils;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SearcHistoryAdapter;
import com.example.ks_internship.app.utils.adapter.SongListAdapter;
import com.example.ks_internship.app.utils.lisners.OnSongListener;
import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ChoiceFragment extends Fragment {


    public OnSongListener onSongListener;
    private RecyclerView recyclerView;
    private View loaderBlock;
    private AppCompatButton goButton;
    private AppCompatEditText titleTrackInput;

    private ArrayList<DeezerTrack> deezerTrackArrayList;
    private List<String> titleSearch;
    private SongListAdapter songListAdapter;
    private DeezerResponse deezerResponse;
    private LinearLayoutManager layoutManager;
    private int nextCount;
    Gson gson;
    AppDatabase db;

    private OnSongRecycleClickListener onSongRecycleClickListener = new OnSongRecycleClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            onSongListener.setSong(songListAdapter.getItems().get(position));
        }

        @Override
        public void onItemDeleteClick(View v, int position) {
            new AlertDialog.Builder(v.getContext())
                    .setMessage(getString(R.string.want_delete))
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.getPersonDao().deleteTrack(deezerTrackArrayList.get(position));
                            songListAdapter.notifyItemRemoved(position);


                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), null)
                    .create().show();


        }

        @Override
        public void onItemShareClick(View v, int position) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, songListAdapter.getItems().get(position).toString());
            startActivity(Intent.createChooser(intent, null));
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
    public void onStart() {
        super.onStart();

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choice, container, false);

        initVeiw(v);
        setListener();

        gson = new Gson();
        deezerTrackArrayList = new ArrayList<>();
        titleSearch = new ArrayList<>();
        songListAdapter = new SongListAdapter(deezerTrackArrayList, v.getContext());

        String jsonText = SaveSearchHistory.getTitles(v.getContext());
        if (!TextUtils.isEmpty(jsonText)) {
            titleSearch.addAll(Arrays.asList(gson.fromJson(jsonText, String[].class)));
        }
        nextCount = 0;

        songListAdapter.setListener(onSongRecycleClickListener);
        recyclerView.setAdapter(songListAdapter);

        db = AppKsInternship.getInstance().getDatabase();
        db.getPersonDao().getAllTrackS().observe(this, getTrackes -> {
            deezerTrackArrayList.clear();
            deezerTrackArrayList.addAll(getTrackes);
            songListAdapter.notifyDataSetChanged();
        });

       titleTrackInput.setText(SaveSearchHistory.getInputSearch(getContext()));
        return v;
    }


    public void setListener() {

        goButton.setOnClickListener(view -> {
            searchAction();
        });

        titleTrackInput.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_GO) {
                searchAction();
                return true;
            }
            return false;
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    if ((layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition()) >= layoutManager.getItemCount()) {
                        nextSearchAction();
                    }
                }
            }


        });
    }

    public void initVeiw(View v) {
        recyclerView = v.findViewById(R.id.my_recycler_view);

        loaderBlock = v.findViewById(R.id.loader_block);
        goButton = v.findViewById(R.id.btn_go);
        titleTrackInput = v.findViewById(R.id.et_titleTrack_input);

    }

    public void setResult(String string) {
        titleTrackInput.setText(string);
        loadRepos(string);


    }

    public void searchAction() {
        String title = titleTrackInput.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            titleTrackInput.requestFocus();
        } else {
            KeyboardUtils.hide(titleTrackInput);
            loadRepos(title);
            titleSearch.add(title);
            SaveSearchHistory.setTitleSearch(getContext(), gson.toJson(titleSearch));
        }
    }

    public void nextSearchAction() {
       String title = SaveSearchHistory.getInputSearch(getContext());
       if(deezerResponse!=null) {
           if (!TextUtils.isEmpty(deezerResponse.getNext())) {
               nextCount = nextCount + 25;
               nextLoadRepos(title, nextCount);

           }
       }

    }

    public void nextLoadRepos(String string, int nextCount) {

        showProgressBlock();
        RestClient.getsInstance().getService().getData(string, nextCount).enqueue(new ApiCallback<DeezerResponse>() {
            @Override
            public void success(Response<DeezerResponse> response) {
                db.getPersonDao().insertAllTracks(response.body().getData());
                songListAdapter.notifyDataSetChanged();
                deezerResponse = response.body();
                hideProgressBlock();

            }

            @Override
            public void failure(DeezerRepoErrorItem deezerRepoErrorItem) {
                if (TextUtils.isEmpty(deezerRepoErrorItem.getCode())) {
                    makeErrorToast("Error occurred during request: " + deezerRepoErrorItem.getMessage());
                } else {
                    makeErrorToast(deezerRepoErrorItem.getMessage() + "Code error:" + deezerRepoErrorItem.getCode());
                }

                hideProgressBlock();
            }


        });
    }


    public void loadRepos(String title) {
        showProgressBlock();
        RestClient.getsInstance().getService().getData(title).enqueue(new ApiCallback<DeezerResponse>() {
            @Override
            public void success(Response<DeezerResponse> response) {
                db.getPersonDao().deleteAllTracks();
                nextCount = 0;
                db.getPersonDao().insertAllTracks(response.body().getData());
                songListAdapter.notifyDataSetChanged();
                deezerResponse = response.body();
                hideProgressBlock();
                SaveSearchHistory.setInputSearch(getContext(),title);
            }

            @Override
            public void failure(DeezerRepoErrorItem deezerRepoErrorItem) {
                if (TextUtils.isEmpty(deezerRepoErrorItem.getCode())) {
                    makeErrorToast("Error occurred during request: " + deezerRepoErrorItem.getMessage());
                } else {
                    makeErrorToast(deezerRepoErrorItem.getMessage() + "Code error:" + deezerRepoErrorItem.getCode());
                }

                hideProgressBlock();
            }


        });
    }


    private void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }


    public void setOnSongListener(OnSongListener onSongListener) {
        this.onSongListener = onSongListener;
    }
}