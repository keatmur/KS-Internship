package com.example.ks_internship.app.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ks_internship.R;
import com.example.ks_internship.app.activity.ThirdActivity;
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

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ChoiceFragment extends Fragment {

    public OnSongListener onSongListener;
    private RecyclerView recyclerView;
    private View loaderBlock;
    private ArrayList<DeezerTrack> deezerTrackArrayList;
    private SongListAdapter songListAdapter;
    private FloatingActionButton addFab;
    private AppCompatButton goButton;
    private ProgressBar progressBar;
    private AppCompatEditText titleTrackInput;


    private OnSongRecycleClickListener onSongRecycleClickListener = new OnSongRecycleClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            onSongListener.setSong(songListAdapter.getItems().get(position).toString());
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

        @Override
        public void onItemEbitClick(View v, int position) {

            DeezerTrack deezerTrack = songListAdapter.getItems().get(position);
            Intent intent = new Intent(v.getContext(), ThirdActivity.class);
            intent.putExtra(Constants.EXTRA_EBIT_POSITION, position);
            startActivityForResult(intent, Constants.RESULT_COD_EBIT);


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


        initVeiw(v);

        deezerTrackArrayList =  new ArrayList<>();

        addFab.setOnClickListener(i -> {
            Intent intent = new Intent(v.getContext(), ThirdActivity.class);
            startActivityForResult(intent, Constants.RESULT_COD);
        });

        goButton.setOnClickListener(view -> {
            SearchAction();
        });

        songListAdapter = new SongListAdapter(deezerTrackArrayList, v.getContext());
        songListAdapter.setListener(onSongRecycleClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(songListAdapter);


        return v;
    }

    public void initVeiw(View v) {
        recyclerView = v.findViewById(R.id.my_recycler_view);
        addFab = v.findViewById(R.id.addFab);
        loaderBlock = v.findViewById(R.id.loader_block);
        goButton = v.findViewById(R.id.btn_go);
        titleTrackInput = v.findViewById(R.id.et_titleTrack_input);

    }


    public void SearchAction() {

        if (TextUtils.isEmpty(titleTrackInput.getText().toString().trim())) {
            titleTrackInput.requestFocus();
        } else {
            KeyboardUtils.hide(titleTrackInput);
            loadRepos(titleTrackInput.getText().toString().trim());
        }
    }

    public void loadRepos(String title) {
        showProgressBlock();
        RestClient.getsInstance().getService().getData(title).enqueue(new Callback<DeezerResponse>() {

            @Override
            public void onResponse(@NotNull Call<DeezerResponse> call, @NotNull Response<DeezerResponse> response) {

                if (!response.isSuccessful()) {
                    Converter<ResponseBody, DeezerRepoErrorItem> converter = RestClient.getsInstance().getRetrofit().responseBodyConverter(DeezerRepoErrorItem.class, new Annotation[0]);

                    try {
                        DeezerRepoErrorItem repoError = converter.convert(response.errorBody());
                        makeErrorToast(repoError.getMessage() + " \n Details: " + repoError.getCode());
                    } catch (Exception e) {
                        makeErrorToast("Unhandled error. Code: " + response.code());
                    }

                    hideProgressBlock();
                    return;

                }

                deezerTrackArrayList.clear();
                deezerTrackArrayList.addAll(response.body().getData());
                songListAdapter.notifyDataSetChanged();
                hideProgressBlock();

            }


            @Override
            public void onFailure(@NotNull Call<DeezerResponse> call, Throwable t) {

                makeErrorToast("Error occurred during request: " + t.getMessage());
                t.printStackTrace();
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