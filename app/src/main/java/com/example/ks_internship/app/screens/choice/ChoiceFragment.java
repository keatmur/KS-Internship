package com.example.ks_internship.app.screens.choice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.ks_internship.R;

import com.example.ks_internship.app.api.ApiCallback;
import com.example.ks_internship.app.api.RestClient;
import com.example.ks_internship.app.database.AppDatabase;
import com.example.ks_internship.app.model.DeezerRepoErrorItem;
import com.example.ks_internship.app.model.DeezerResponse;
import com.example.ks_internship.app.model.DeezerTrack;

import com.example.ks_internship.app.utils.KeyboardUtils;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SongListAdapter;
import com.example.ks_internship.app.utils.lisners.OnSongListener;
import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;


import java.util.ArrayList;
import java.util.List;


public class ChoiceFragment extends Fragment  implements  ChoiceContract.View{


    public OnSongListener onSongListener;
    private RecyclerView recyclerView;
    private View loaderBlock;
    private AppCompatButton goButton;
    private AppCompatEditText titleTrackInput;

    private ArrayList<DeezerTrack> deezerTrackArrayList;
    private SongListAdapter songListAdapter;
    private DeezerResponse deezerResponse;
    private LinearLayoutManager layoutManager;

    private ChoiceContract.Presenter presenter;


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
                            presenter.deleteTrack(deezerTrackArrayList.get(position));
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

    @Override
    public void setPresenter(ChoiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

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

        deezerTrackArrayList = new ArrayList<>();
        songListAdapter = new SongListAdapter(deezerTrackArrayList, v.getContext());
        songListAdapter.setListener(onSongRecycleClickListener);
        recyclerView.setAdapter(songListAdapter);

        presenter.takeView(this);
        return v;
    }


    public void setListener() {

        goButton.setOnClickListener(view -> {
            presenter.search(titleTrackInput.getText().toString().trim());
        });

        titleTrackInput.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_GO) {
                presenter.search(titleTrackInput.getText().toString().trim());
                return true;
            }
            return false;
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    if ((layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition()) >= layoutManager.getItemCount()) {
                        presenter.nextSearch();
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


    @Override
    public void observeItems(LiveData<List<DeezerTrack>> liveData) {

        liveData.observe(ChoiceFragment.this, getTrackes -> {
            deezerTrackArrayList.clear();
            deezerTrackArrayList.addAll(getTrackes);
            songListAdapter.notifyDataSetChanged();
        });
    }
    @Override
    public void stopObserveItems(LiveData<List<DeezerTrack>> liveData)  {
        liveData.removeObservers(ChoiceFragment.this);
    }


    public void setResult(String string) {
        titleTrackInput.setText(string);
        presenter.loadRepos(string);
    }


    @Override
    public void  showErorrInput(){
        titleTrackInput.requestFocus();
    }

    @Override
    public void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard(){
        KeyboardUtils.hide(titleTrackInput);

    }

    @Override
    public void setLostSearch(String lostSearch) {
        titleTrackInput.setText(lostSearch);
    }

    @Override
    public void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }


    public void setOnSongListener(OnSongListener onSongListener) {
        this.onSongListener = onSongListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }



}