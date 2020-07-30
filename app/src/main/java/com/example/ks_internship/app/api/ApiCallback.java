package com.example.ks_internship.app.api;

import com.example.ks_internship.app.model.DeezerRepoErrorItem;
import com.example.ks_internship.app.model.DeezerResponse;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

   public abstract void success(Response<T> response);

    public abstract void failure(DeezerRepoErrorItem deezerRepoErrorItem);

    @Override
    public void onResponse( Call<T> call,  Response<T> response) {

        if (!response.isSuccessful()) {
            Converter<ResponseBody, DeezerRepoErrorItem> converter = RestClient.getsInstance().getRetrofit().responseBodyConverter(DeezerRepoErrorItem.class, new Annotation[0]);
            try {
                DeezerRepoErrorItem repoError = converter.convert(response.errorBody());
                failure(repoError);

            } catch (Exception e) {
                failure(new DeezerRepoErrorItem("Unhandled error! Code: " + response.code()));

            }
        }else{
                success(response);
            }



    }

    @Override
    public void onFailure(@NotNull Call<T> call, Throwable t) {
       failure(new DeezerRepoErrorItem("Unexpected error! Info: " + t.getMessage()));

    }
}
