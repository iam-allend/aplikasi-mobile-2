package com.example.restfull_api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {
    @GET("api.php")
    Call<List<Mahasiswa>> getMahasiswa();
}