package com.example.restfull_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegisterAPI {
    @GET("get_mahasiswa.php")
    Call<Value> view();
}