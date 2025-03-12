package com.example.restfull_api;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2/aplikasi_mobile_1/"; // Perbaikan URL
    private List<DataMahasiswa> results = new ArrayList<>();
    private MahasiswaAdapter viewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewAdapter = new MahasiswaAdapter(this, results);
        recyclerView.setAdapter(viewAdapter);

        loadDataMahasiswa();
    }

    private void loadDataMahasiswa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.view();
        Log.i("Info Load", "Load Data Mahasiswa Diakses");

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if (response.isSuccessful() && response.body() != null) {
                    results.clear();
                    results.addAll(response.body().getResult());
                    viewAdapter.notifyDataSetChanged(); // Perbarui data tanpa membuat adapter baru
                    Log.i("Info Load", "Data berhasil dimuat: " + results.size());
                } else {
                    Log.e("Info Load", "Respon tidak valid");
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.e("Info Load", "Gagal memuat data: " + t.getMessage());
            }
        });
    }
}