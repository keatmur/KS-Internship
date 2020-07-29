package com.example.ks_internship.app.api;

import com.example.ks_internship.app.model.DeezerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search/track")
    Call<DeezerResponse> getData(@Query("q") String query);

}