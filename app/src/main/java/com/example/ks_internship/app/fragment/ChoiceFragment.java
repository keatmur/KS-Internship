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
import com.example.ks_internship.app.activity.ThirdActivity;
import com.example.ks_internship.app.api.ApiCallback;
import com.example.ks_internship.app.api.RestClient;
import com.example.ks_internship.app.model.DeezerRepoErrorItem;
import com.example.ks_internship.app.model.DeezerResponse;
import com.example.ks_internship.app.model.DeezerTrack;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.KeyboardUtils;
import com.example.ks_internship.app.utils.adapter.SongListAdapter;
import com.example.ks_internship.app.utils.lisners.OnSongListener;
import com.example.ks_internship.app.utils.lisners.OnSongRecycleClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ChoiceFragment extends Fragment {


    public OnSongListener onSongListener;
    private RecyclerView recyclerView;
    private View loaderBlock;
    private AppCompatButton goButton;
    private AppCompatEditText titleTrackInput;

    private ArrayList<DeezerTrack> deezerTrackArrayList;
    private SongListAdapter songListAdapter;
    private DeezerResponse deezerResponse;
    private LinearLayoutManager layoutManager;
    private int nextCount;


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
                            deezerTrackArrayList.remove(position);
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

        layoutManager = new LinearLayoutManager(getView().getContext());
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
        nextCount = 0;

        songListAdapter.setListener(onSongRecycleClickListener);
        recyclerView.setAdapter(songListAdapter);


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

    public void searchAction() {

        if (TextUtils.isEmpty(titleTrackInput.getText().toString().trim())) {
            titleTrackInput.requestFocus();
        } else {
            KeyboardUtils.hide(titleTrackInput);
            loadRepos(titleTrackInput.getText().toString().trim());
        }
    }

    public void nextSearchAction() {
        if (!TextUtils.isEmpty(deezerResponse.getNext())) {
            nextCount = nextCount + 25;
            if (TextUtils.isEmpty(titleTrackInput.getText().toString().trim())) {
                titleTrackInput.requestFocus();
            } else {
                KeyboardUtils.hide(titleTrackInput);
                nextLoadRepos(titleTrackInput.getText().toString().trim(), nextCount);
            }

        }
    }

    public void nextLoadRepos(String string, int nextCount) {

        showProgressBlock();
        RestClient.getsInstance().getService().getData(string, nextCount).enqueue(new ApiCallback<DeezerResponse>() {
            @Override
            public void success(Response<DeezerResponse> response) {
                deezerTrackArrayList.addAll(response.body().getData());
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
                deezerTrackArrayList.clear();
                nextCount = 0;
                deezerTrackArrayList.addAll(response.body().getData());
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


    private void makeErrorToast(String errorMessage) {
        Toast.makeText(getView().getContext(), errorMessage, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RESULT_COD) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    deezerTrackArrayList.add(data.getExtras().getParcelable(Constants.EXTRA_MESSAGE));
                    songListAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(recyclerView.getBottom());

                }
            }
        }
        if (requestCode == Constants.RESULT_COD_EBIT) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    deezerTrackArrayList.set(data.getExtras().getInt(Constants.EXTRA_EBIT_POSITION), data.getExtras().getParcelable(Constants.EXTRA_MESSAGE));
                    songListAdapter.notifyItemChanged(data.getExtras().getInt(Constants.EXTRA_EBIT_POSITION));


                }
            }
        }
    }

    public void setOnSongListener(OnSongListener onSongListener) {
        this.onSongListener = onSongListener;
    }
}